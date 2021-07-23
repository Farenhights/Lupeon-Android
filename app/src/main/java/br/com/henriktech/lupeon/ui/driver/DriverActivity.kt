package br.com.henriktech.lupeon.ui.driver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.ui.login.LoginActivity

class DriverActivity : AppCompatActivity(R.layout.activity_driver) {
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(baseContext, LoginActivity::class.java))
        finish()
    }
}