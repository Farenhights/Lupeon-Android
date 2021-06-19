package br.com.henriktech.lupeon.analytics.provider

import android.app.Activity
import android.os.Bundle
import br.com.henriktech.lupeon.analytics.AnalyticsProvider
import com.google.firebase.analytics.FirebaseAnalytics

internal class FirebaseAnalyticsProvider(
    private val firebaseAnalytics: FirebaseAnalytics
) : AnalyticsProvider {

    override fun trackEvent(eventName: String, params: Map<String, String>) {
        val bundle = Bundle().apply {
            params.forEach {
                putString(it.key, it.value)
            }
        }
        if (eventName.length > MAX_CHARACTER) {
            firebaseAnalytics.logEvent(eventName.take(MAX_CHARACTER), bundle)
        } else {
            firebaseAnalytics.logEvent(eventName, bundle)
        }
    }

    override fun trackScreen(activity: Activity, screenName: String, params: Map<String, String>) {
        if (screenName.length > MAX_CHARACTER) {
            firebaseAnalytics.setCurrentScreen(activity, screenName.take(MAX_CHARACTER), null)
        } else {
            firebaseAnalytics.setCurrentScreen(activity, screenName, null)
        }
    }

    private companion object {
        private const val MAX_CHARACTER = 40
    }

}
