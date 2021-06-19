package br.com.henriktech.lupeon.ui.login.losspassword

import android.app.Activity
import br.com.henriktech.lupeon.analytics.Analytics
import br.com.henriktech.lupeon.ui.login.presentention.PresentationAnalytics

class LossPasswordAnalytics(private val analytics: Analytics) {

    fun trackScreen(activity: Activity) {
        analytics.trackScreen(
            activity,
            EVENT_NAME
        )
    }

    fun clickBackButton() {
        trackEvent("${PresentationAnalytics.FRAG_PRESENTATION}_Clique_Botao_Voltar")
    }

    private fun trackEvent(eventName: String) {
        analytics.trackEvent(eventName)
    }

    private companion object {
        private const val SCREEN_NAME = "Login"
        internal const val FRAG_NAME = "${SCREEN_NAME}_LossPassword"
        private const val EVENT_NAME = "${FRAG_NAME}_Tela_aberta"
    }
}