package br.com.henriktech.lupeon.ui.tracking

import android.app.Activity
import br.com.henriktech.lupeon.analytics.Analytics

class TrackingAnalytics(private val analytics: Analytics) {
    fun trackScreen(activity: Activity) {
        analytics.trackScreen(
            activity,
            EVENT_PRESENTATION
        )
    }

    private fun trackEvent(eventName: String) {
        analytics.trackEvent(eventName)
    }

    internal companion object {
        private const val SCREEN_NAME = "Profile"
        internal const val FRAG_PRESENTATION = "${SCREEN_NAME}_Tracking"
        private const val EVENT_PRESENTATION = "${FRAG_PRESENTATION}_Tela_aberta"
    }
}