package br.com.henriktech.lupeon.ui.login.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.api.model.Login
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.service.AuthenticationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginMainViewModel(private val authenticationService: AuthenticationService) : ViewModel() {

    val login: MutableLiveData<Login> by lazy {
        MutableLiveData<Login>()
    }

    val  errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun validateLogin(user: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (user.isNotEmpty() && password.isNotEmpty()) {
                when (val response = authenticationService.validateLogin(user, password)) {
                    is ApiResult.Success -> {
                        login.postValue(response.data)
                    }
                    is ApiResult.Error -> {
                        errorMessage.postValue(response.message)
                    }
                }
            } else {
                errorMessage.postValue("Erro ao realizar login!")
            }
        }
    }
}