package br.com.henriktech.lupeon.analytics.provider

import android.app.Activity
import android.app.Application
import br.com.henriktech.lupeon.analytics.AnalyticsProvider
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class AppCenterAnalyticsProvider(private val application: Application) : AnalyticsProvider {
    override fun trackScreen(activity: Activity, screenName: String, params: Map<String, String>) {
        Analytics.trackEvent(screenName)
    }

    override fun trackEvent(eventName: String, params: Map<String, String>) {
        Analytics.trackEvent(eventName)
    }

    private companion object {
        private const val APP_KEY = "37bd9f83-87a3-4072-a0b2-e37ab167c619"
    }

    init {
        AppCenter.start(application, APP_KEY, Analytics::class.java, Crashes::class.java)
    }
}