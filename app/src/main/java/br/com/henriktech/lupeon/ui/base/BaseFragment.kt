package br.com.henriktech.lupeon.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.henriktech.lupeon.api.model.Login
import br.com.henriktech.lupeon.ui.driver.DriverActivity
import br.com.henriktech.lupeon.ui.login.LoginActivity
import br.com.henriktech.lupeon.ui.profile.ProfileActivity

open class BaseFragment(id: Int) : Fragment(id) {

    private val LOGIN = "LOGIN"
    private val BUNDLE = "BUNDLE"

    fun loginApplication(login: Login) {
        val intent =
            when (login.TipoUsuario) {
                "M" -> Intent(context, DriverActivity::class.java)
                else -> Intent(context, ProfileActivity::class.java)
            }
        val bundle = Bundle()
        bundle.putParcelable(LOGIN, login)
        intent.putExtra(BUNDLE, bundle)
        startActivity(intent)
    }

    fun getLoginActive(): Login {
        val bundle = requireActivity().intent.getBundleExtra(BUNDLE)
        return bundle!!.getParcelable<Login>(LOGIN)!!
    }

    fun logoutApplication() {
        val intent = Intent(context, LoginActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(LOGIN, null)
        intent.putExtra(BUNDLE, bundle)
        requireActivity().finish()
        startActivity(intent)
    }
}

interface IOnBackPressed {
    fun onBackPressed(): Boolean
}