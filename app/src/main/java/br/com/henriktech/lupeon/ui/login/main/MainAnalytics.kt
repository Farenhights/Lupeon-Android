package br.com.henriktech.lupeon.ui.login.main

import android.app.Activity
import br.com.henriktech.lupeon.analytics.Analytics
import br.com.henriktech.lupeon.ui.login.presentention.PresentationAnalytics

class MainAnalytics(private val analytics: Analytics) {

    fun trackScreen(activity: Activity) {
        analytics.trackScreen(
            activity,
            EVENT_PRESENTATION
        )
    }

    fun clickBackButton() {
        trackEvent("${PresentationAnalytics.FRAG_PRESENTATION}_Clique_Botao_Voltar")
    }

    fun clickLossPasswordButton() {
        trackEvent("${PresentationAnalytics.FRAG_PRESENTATION}_Clique_Botao_Esqueci_Senha")
    }

    fun clickEnterButton() {
        trackEvent("${PresentationAnalytics.FRAG_PRESENTATION}_Clique_Botao_Entrar")
    }

    private fun trackEvent(eventName: String) {
        analytics.trackEvent(eventName)
    }

    private companion object {
        private const val SCREEN_NAME = "Login"
        const val FRAG_PRESENTATION = "${SCREEN_NAME}_Main"
        private const val EVENT_PRESENTATION = "${FRAG_PRESENTATION}_Tela_aberta"
    }
}