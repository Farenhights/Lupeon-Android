package br.com.henriktech.lupeon.ui.login.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.model.Profile
import br.com.henriktech.lupeon.data.service.AuthenticationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(private val authenticationService: AuthenticationService) : ViewModel() {

    private val token: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val tokenType: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val userType: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val activityLiveData: MutableLiveData<Profile> by lazy {
        MutableLiveData<Profile>()
    }

    fun validateLogin(user: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = authenticationService.validateLogin(user, password)) {
                is ApiResult.Success -> {
                    token.postValue(response.data!!.token)
                    tokenType.postValue(response.data!!.tokenType)
                    userType.postValue(response.data!!.userType)
                }
                is ApiResult.Error -> {
                    token.postValue(null)
                }
            }
        }
    }

    fun setProfile(user: String) {
        val profile = user.toUpperCase(Locale.ROOT)

        when (profile) {
            "EMBARCADOR" -> activityLiveData.postValue(Profile.SHIPPER)
            "TRASNPORTADOR" -> activityLiveData.postValue(Profile.TRANSPORTER)
            else -> activityLiveData.postValue(Profile.DRIVER)
        }
    }
}