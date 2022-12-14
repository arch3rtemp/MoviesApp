package com.arch3rtemp.android.moviesapp.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.arch3rtemp.android.moviesapp.R
import com.arch3rtemp.android.moviesapp.util.delayAction
import com.arch3rtemp.android.moviesapp.util.showSnackbar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var splashScreen: SplashScreen
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var navController: NavController
    private var isLoggedIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        splashScreen.setKeepOnScreenCondition { true }
        initNavigationWithToolbar()
        navigationDecision()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return if (isLoggedIn) {
            val inflater = menuInflater
            inflater.inflate(R.menu.main_menu, menu)
            true
        } else super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.log_out -> {
                logOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initNavigationWithToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.loginFragment,
            R.id.listFragment
        ).build()
        toolbar.setupWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> {
                    isLoggedIn = false
                    invalidateOptionsMenu()
                }
                else -> {
                    isLoggedIn = true
                    invalidateOptionsMenu()
                }
            }
        }
    }

    private fun navigationDecision() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.hasToken.observe(this@MainActivity) { hasToken ->
                    if (!hasToken) {
                        navController.navigate(R.id.action_global_loginFragment)
                    }

                    delayAction(DELAY_SHOW_SCREEN) {
                        splashScreen.setKeepOnScreenCondition { false }
                    }
                }

                viewModel.errorChannel.collectLatest {
                    splashScreen.setKeepOnScreenCondition { false }
                    Snackbar.make(toolbar, it.asString(this@MainActivity), Snackbar.LENGTH_SHORT)
                        .apply {
                            animationMode = Snackbar.ANIMATION_MODE_SLIDE
                            toolbar.setBackgroundColor(
                                ContextCompat.getColor(
                                    this@MainActivity,
                                    R.color.colorSnackBarError
                                )
                            )
                            show()
                        }
                    delayAction(DELAY_SHOW_SCREEN) {
                        splashScreen.setKeepOnScreenCondition { false }
                    }
                }
            }
        }
    }

    private fun logOut() {
        viewModel.logoutUser()
    }

    companion object {
        private const val DELAY_SHOW_SCREEN = 500L
    }
}