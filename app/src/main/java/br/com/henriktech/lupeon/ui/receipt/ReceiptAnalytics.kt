package br.com.henriktech.lupeon.ui.receipt

import android.app.Activity
import br.com.henriktech.lupeon.analytics.Analytics

class ReceiptAnalytics(private val analytics: Analytics) {
    fun trackScreen(activity: Activity) {
        analytics.trackScreen(
            activity,
            EVENT_PRESENTATION
        )
    }

    fun clickButton(option: String) {
        trackEvent("${ReceiptAnalytics.SCREEN_NAME}_Clique_$option")
    }

    private fun trackEvent(eventName: String) {
        analytics.trackEvent(eventName)
    }

    internal companion object {
        private const val SCREEN_NAME = "Comprovante"
        private const val EVENT_PRESENTATION = "${SCREEN_NAME}_Tela_aberta"
    }
}