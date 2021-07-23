package br.com.henriktech.lupeon.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.ui.login.LoginActivity
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    private val analytics: SplashAnalytics by inject()
    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onStart() {
        super.onStart()
        analytics.trackScreen(this)
        activityScope.launch {
            delay(2500)
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}