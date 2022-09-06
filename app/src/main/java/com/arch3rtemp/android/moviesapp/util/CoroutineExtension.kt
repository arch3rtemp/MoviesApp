package com.arch3rtemp.android.moviesapp.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun CoroutineScope.delayAction(timeMillis: Long, action: () -> Unit) {
    this.launch {
        delay(timeMillis)
        action()
    }
}