package br.com.henriktech.lupeon.util

import android.text.TextUtils
import android.util.Patterns

object EmailUtil {
    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}