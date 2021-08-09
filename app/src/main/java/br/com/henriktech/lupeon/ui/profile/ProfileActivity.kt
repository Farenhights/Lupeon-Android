package br.com.henriktech.lupeon.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.ui.login.LoginActivity

class ProfileActivity : AppCompatActivity(R.layout.activity_profile){
    override fun onBackPressed() {
        val fragment = this.supportFragmentManager.findFragmentById(R.id.profileMenuFragment)
        (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let {
            super.onBackPressed()
            val intent = Intent(this, LoginActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("LOGIN", null)
            intent.putExtra("BUNDLE", bundle)
            startActivity(intent)
            finish()
        }
    }
}

interface IOnBackPressed {
    fun onBackPressed(): Boolean
}