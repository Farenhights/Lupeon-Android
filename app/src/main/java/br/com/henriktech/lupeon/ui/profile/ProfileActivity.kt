package br.com.henriktech.lupeon.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.ui.login.LoginActivity
import br.com.henriktech.lupeon.ui.profile.menu.ProfileMenuFragment

class ProfileActivity : AppCompatActivity(R.layout.activity_profile) {
    override fun onBackPressed() {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_profile_fragment)
        navHost?.let { navFragment ->
            navFragment.childFragmentManager.primaryNavigationFragment?.let { fragment ->
                if (fragment is ProfileMenuFragment) {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    super.onBackPressed()
                }
            }
        }
    }
}

interface IOnBackPressed {
    fun onBackPressed(): Boolean
}