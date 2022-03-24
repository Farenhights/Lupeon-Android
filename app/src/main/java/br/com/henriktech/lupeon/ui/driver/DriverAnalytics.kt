package br.com.henriktech.lupeon.ui.driver

import android.app.Activity
import br.com.henriktech.lupeon.analytics.Analytics
import br.com.henriktech.lupeon.ui.menu.MenuAnalytics

class DriverAnalytics(private val analytics: Analytics) {
    fun trackScreen(activity: Activity) {
        analytics.trackScreen(
            activity,
            EVENT_PRESENTATION
        )
    }

    fun clickMenu(option: String) {
        trackEvent("${MenuAnalytics.FRAG_PRESENTATION}_Clique_Menu_$option")
    }

    private fun trackEvent(eventName: String) {
        analytics.trackEvent(eventName)
    }

    internal companion object {
        private const val SCREEN_NAME = "Profile"
        internal const val FRAG_PRESENTATION = "${SCREEN_NAME}_Menu_Motorista"
        private const val EVENT_PRESENTATION = "${FRAG_PRESENTATION}_Tela_aberta"
    }
}