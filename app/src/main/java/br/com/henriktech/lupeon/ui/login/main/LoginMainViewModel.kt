package br.com.henriktech.lupeon.ui.login.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.api.model.Login
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.model.User
import br.com.henriktech.lupeon.data.model.toUserEntity
import br.com.henriktech.lupeon.data.service.AuthenticationService
import br.com.henriktech.lupeon.database.repository.UserDbDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginMainViewModel(
    private val authenticationService: AuthenticationService,
    private val userDbDataSource: UserDbDataSource
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun validateLogin(user: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (user.isNotEmpty() && password.isNotEmpty()) {
                when (val response = authenticationService.validateLogin(user, password)) {
                    is ApiResult.Success<*> -> {
                        saveUser(response.data!!)
                    }
                    is ApiResult.Error -> {
                        _errorMessage.postValue(response.message)
                    }
                }
            } else {
                _errorMessage.postValue("Erro ao realizar login!")
            }
        }
    }

    private fun saveUser(login: Login) {
        userDbDataSource.createUser(login.toUserEntity())
        _user.postValue(userDbDataSource.getUser(login.usuarioId))
    }
}