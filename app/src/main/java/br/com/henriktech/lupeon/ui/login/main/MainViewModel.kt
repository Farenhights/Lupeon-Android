package br.com.henriktech.lupeon.ui.login.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.service.AuthenticationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val authenticationService: AuthenticationService) : ViewModel() {

    val token: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val tokenType: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val userType: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
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

}