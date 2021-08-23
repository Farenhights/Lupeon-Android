package br.com.henriktech.lupeon.ui.profile

import androidx.appcompat.app.AppCompatActivity
import br.com.henriktech.lupeon.R

class ProfileActivity : AppCompatActivity(R.layout.activity_profile)

interface IOnBackPressed {
    fun onBackPressed(): Boolean
}