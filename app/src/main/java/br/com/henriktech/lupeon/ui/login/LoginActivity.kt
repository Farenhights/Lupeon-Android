package br.com.henriktech.lupeon.ui.login

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import br.com.henriktech.lupeon.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000)
        setTheme(R.style.Theme_Lupeon)
        setContentView(R.layout.activity_login)
    }
}