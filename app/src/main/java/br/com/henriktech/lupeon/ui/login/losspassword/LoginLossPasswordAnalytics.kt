package br.com.henriktech.lupeon.ui.login.losspassword

import android.app.Activity
import br.com.henriktech.lupeon.analytics.Analytics
import br.com.henriktech.lupeon.ui.login.presentention.LoginPresentationAnalytics

class LoginLossPasswordAnalytics(private val analytics: Analytics) {

    fun trackScreen(activity: Activity) {
        analytics.trackScreen(
            activity,
            EVENT_NAME
        )
    }

    fun clickBackButton() {
        trackEvent("${LoginPresentationAnalytics.FRAG_PRESENTATION}_Clique_Botao_Voltar")
    }

    fun clickSendEmailButton() {
        trackEvent("${LoginPresentationAnalytics.FRAG_PRESENTATION}_Clique_Botao_Enviar_Email")
    }

    fun clickHaveCodeButton() {
        trackEvent("${LoginPresentationAnalytics.FRAG_PRESENTATION}_Clique_Botao_Possuo_Codigo")
    }

    private fun trackEvent(eventName: String) {
        analytics.trackEvent(eventName)
    }

    private companion object {
        private const val SCREEN_NAME = "Login"
        const val FRAG_NAME = "${SCREEN_NAME}_LossPassword"
        private const val EVENT_NAME = "${FRAG_NAME}_Tela_aberta"
    }
}