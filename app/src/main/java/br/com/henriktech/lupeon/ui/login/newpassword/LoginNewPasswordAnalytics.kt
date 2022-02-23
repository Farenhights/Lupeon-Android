package br.com.henriktech.lupeon.ui.login.newpassword

import android.app.Activity
import br.com.henriktech.lupeon.analytics.Analytics

class LoginNewPasswordAnalytics(private val analytics: Analytics) {


    fun trackScreen(activity: Activity) {
        analytics.trackScreen(
            activity,
            LoginNewPasswordAnalytics.EVENT_PRESENTATION
        )
    }

    fun clickBackButton() {
        trackEvent("${LoginNewPasswordAnalytics.FRAG_PRESENTATION}_Clique_Botao_Voltar")
    }

    fun clickNewPasswordButton() {
        trackEvent("${LoginNewPasswordAnalytics.FRAG_PRESENTATION}_Clique_Botao_Nova_Senha")
    }

    private fun trackEvent(eventName: String) {
        analytics.trackEvent(eventName)
    }

    private companion object {
        private const val SCREEN_NAME = "Login"
        const val FRAG_PRESENTATION = "${SCREEN_NAME}_New_Password"
        private const val EVENT_PRESENTATION = "${FRAG_PRESENTATION}_Tela_aberta"
    }
}