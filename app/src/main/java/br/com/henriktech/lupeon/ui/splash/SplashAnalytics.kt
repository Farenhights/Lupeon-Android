package br.com.henriktech.lupeon.ui.splash

import android.app.Activity
import br.com.henriktech.lupeon.analytics.Analytics

class SplashAnalytics(private val analytics: Analytics) {

    fun trackScreen(activity: Activity) {
        analytics.trackScreen(
            activity,
            EVENT_SCREEN_NAME
        )
    }

    private companion object {
        private const val SCREEN_NAME = "Splash"
        private const val EVENT_SCREEN_NAME = "${SCREEN_NAME}_Tela_Aberta"
    }
}