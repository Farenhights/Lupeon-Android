package br.com.henriktech.lupeon.ui.invoice

import android.app.Activity
import br.com.henriktech.lupeon.analytics.Analytics

class InvoiceAnalytics(private val analytics: Analytics) {
    fun trackScreen(activity: Activity) {
        analytics.trackScreen(
            activity,
            EVENT_PRESENTATION
        )
    }

    private fun trackEvent(eventName: String) {
        analytics.trackEvent(eventName)
    }

    fun clickBack() {
        trackEvent("${FRAG_PRESENTATION}_click_voltar")
    }

    fun clickBottomMenuShow() {
        trackEvent("${FRAG_PRESENTATION}_click_mostrar_menu")
    }

    fun clickBottomMenuHide() {
        trackEvent("${FRAG_PRESENTATION}_click_esconder_menu")
    }

    fun clickReload() {
        trackEvent("${FRAG_PRESENTATION}_click_recarregar")
    }

    internal companion object {
        private const val SCREEN_NAME = "Notas_Transito"
        internal const val FRAG_PRESENTATION = "${SCREEN_NAME}_Tracking"
        private const val EVENT_PRESENTATION = "${FRAG_PRESENTATION}_Tela_aberta"
    }
}