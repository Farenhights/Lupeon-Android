package br.com.henriktech.lupeon.ui.menu

import android.app.Activity
import br.com.henriktech.lupeon.analytics.Analytics

class MenuAnalytics(private val analytics: Analytics) {
    fun trackScreen(activity: Activity) {
        analytics.trackScreen(
            activity,
            EVENT_PRESENTATION
        )
    }

    private fun trackEvent(eventName: String) {
        analytics.trackEvent(eventName)
    }

    fun clickMenu(option: String) {
        trackEvent("${FRAG_PRESENTATION}_Clique_Menu_$option")
    }

    fun clickAlert(titulo: String) {
        trackEvent("${FRAG_PRESENTATION}_Clique_Alerta_$titulo")
    }

    internal companion object {
        private const val SCREEN_NAME = "Profile"
        internal const val FRAG_PRESENTATION = "${SCREEN_NAME}_Menu"
        private const val EVENT_PRESENTATION = "${FRAG_PRESENTATION}_Tela_aberta"
    }
}