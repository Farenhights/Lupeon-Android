package br.com.henriktech.lupeon.ui.base

import android.content.Intent
import androidx.fragment.app.Fragment
import br.com.henriktech.lupeon.database.repository.UserDbDataSource
import br.com.henriktech.lupeon.database.repository.UserRepository
import br.com.henriktech.lupeon.ui.driver.DriverActivity
import br.com.henriktech.lupeon.ui.login.LoginActivity
import br.com.henriktech.lupeon.ui.profile.ProfileActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseFragment(id: Int) : Fragment(id) {



    fun loginApplication(profile: String) {
        val intent =
            when (profile) {
                "M" -> Intent(context, DriverActivity::class.java)
                else -> Intent(context, ProfileActivity::class.java)
            }
        startActivity(intent)
    }

    fun logoutApplication() {
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}
