package br.com.henriktech.lupeon.ui.login.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.api.model.response.Login
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.model.toAlertEntity
import br.com.henriktech.lupeon.data.model.toMenuEntity
import br.com.henriktech.lupeon.data.model.toUserEntity
import br.com.henriktech.lupeon.data.service.AuthenticationService
import br.com.henriktech.lupeon.database.db.AlertEntity
import br.com.henriktech.lupeon.database.db.MenuEntity
import br.com.henriktech.lupeon.database.repository.AlertRepository
import br.com.henriktech.lupeon.database.repository.MenuRepository
import br.com.henriktech.lupeon.database.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginMainViewModel(
    private val userRepository: UserRepository,
    private val menuRepository: MenuRepository,
    private val alertRepository: AlertRepository,
    private val authenticationService: AuthenticationService
) : ViewModel() {

    private val _perfil = MutableLiveData<String>()
    val perfil: LiveData<String> get() = _perfil

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun validateLogin(user: String, password: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            if (user.isNotEmpty() && password.isNotEmpty()) {
                when (val response = authenticationService.validateLogin(user, password)) {
                    is ApiResult.Success<*> -> {
                        saveUser(response.data!! as Login)
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
        viewModelScope.launch {
            userRepository.createUser(login.toUserEntity())
            val menus = arrayListOf<MenuEntity>()
            login.menus.forEach {
                menus.add(it.toMenuEntity(login.usuarioId))
            }
            menuRepository.deleteAll()
            menuRepository.createListMenu(menus)
            val alets = arrayListOf<AlertEntity>()
            login.alertas.forEach {
                alets.add(it.toAlertEntity(login.usuarioId))
            }
            alertRepository.deleteAll()
            alertRepository.createListAlert(alets)
            _perfil.postValue(login.tipoUsuario)
        }
    }
}