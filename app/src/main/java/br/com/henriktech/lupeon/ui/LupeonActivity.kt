package br.com.henriktech.lupeon.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.ui.login.presentention.LoginPresentationFragment

class LupeonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000)
        setTheme(R.style.Theme_Lupeon)
        setContentView(R.layout.activity_lupeon)
    }

    override fun onBackPressed() {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_login_fragment)
        super.onBackPressed()
        navHost?.let { navFragment ->
            navFragment.childFragmentManager.primaryNavigationFragment?.let { fragment ->
                if (fragment is LoginPresentationFragment) {
                    finish()
                }
            }
        }
    }
}