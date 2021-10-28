package br.com.henriktech.lupeon.ui.base

import android.content.Intent
import androidx.fragment.app.Fragment
import br.com.henriktech.lupeon.ui.login.LoginActivity
import br.com.henriktech.lupeon.ui.profile.ProfileActivity

open class BaseFragment(id: Int) : Fragment(id) {

    fun loginApplication(profile: String) {
        val intent = if (profile.equals("M")) {
            Intent(requireContext(), ProfileActivity::class.java)
        } else {
            // TODO Motorista
            Intent(requireContext(), ProfileActivity::class.java)
        }
        startActivity(intent)
    }

    fun logoutApplication() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        open(intent)
    }

    fun open(intent: Intent) {
        startActivity(intent)
        requireActivity().finish()
    }
}
