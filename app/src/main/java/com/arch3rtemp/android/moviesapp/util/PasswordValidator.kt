package com.arch3rtemp.android.moviesapp.util

import com.arch3rtemp.android.moviesapp.util.Constants.PASSWORD_PATTERN
import java.util.regex.Pattern

object PasswordValidator {

    private val pattern = Pattern.compile(PASSWORD_PATTERN)

    fun isValid(password: String): Boolean {
        return pattern.matcher(password).matches()
    }
}