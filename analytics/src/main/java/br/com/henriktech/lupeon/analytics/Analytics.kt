package br.com.henriktech.lupeon.analytics

import android.app.Activity
import timber.log.Timber

open class Analytics(private val provider: List<AnalyticsProvider>) : AnalyticsProvider {

    override fun trackScreen(activity: Activity, screenName: String, params: Map<String, String>) {
        provider.forEach { it.trackScreen(activity, screenName, params) }
        Timber.tag(TAG).e("Screen - $screenName : $params")
    }

    override fun trackEvent(eventName: String, params: Map<String, String>) {
        provider.forEach { it.trackEvent(eventName, params) }
        Timber.tag(TAG).e("Event - $eventName : $params")
    }

    private companion object {
        private const val TAG = "Analytics"
    }
}
