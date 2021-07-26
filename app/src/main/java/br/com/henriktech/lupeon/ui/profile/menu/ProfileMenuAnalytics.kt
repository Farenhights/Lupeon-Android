package br.com.henriktech.lupeon.ui.profile.menu

import android.app.Activity
import br.com.henriktech.lupeon.analytics.Analytics

class ProfileMenuAnalytics(private val analytics: Analytics) {
    fun trackScreen(activity: Activity) {
        analytics.trackScreen(
            activity,
            EVENT_PRESENTATION
        )
    }

    fun clickEnterButton() {
        trackEvent("${FRAG_PRESENTATION}_Clique_Botao_Entrar")
    }

    private fun trackEvent(eventName: String) {
        analytics.trackEvent(eventName)
    }

    internal companion object {
        private const val SCREEN_NAME = "Profile"
        internal const val FRAG_PRESENTATION = "${SCREEN_NAME}_Menu"
        private const val EVENT_PRESENTATION = "${FRAG_PRESENTATION}_Tela_aberta"
    }
}