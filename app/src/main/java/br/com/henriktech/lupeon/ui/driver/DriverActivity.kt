package br.com.henriktech.lupeon.ui.driver

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.ui.login.LoginActivity

class DriverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(baseContext, LoginActivity::class.java))
        finish()
    }
}