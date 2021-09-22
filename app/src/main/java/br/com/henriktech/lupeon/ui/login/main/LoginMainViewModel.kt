package br.com.henriktech.lupeon.ui.login.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.api.model.Login
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.model.*
import br.com.henriktech.lupeon.data.service.AuthenticationService
import br.com.henriktech.lupeon.database.db.AlertEntity
import br.com.henriktech.lupeon.database.db.AppDataBase
import br.com.henriktech.lupeon.database.db.MenuEntity
import br.com.henriktech.lupeon.database.repository.AlertDbDataSource
import br.com.henriktech.lupeon.database.repository.MenuDbDataSource
import br.com.henriktech.lupeon.database.repository.UserDbDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginMainViewModel(
    private val authenticationService: AuthenticationService
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun validateLogin(user: String, password: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            if (user.isNotEmpty() && password.isNotEmpty()) {
                when (val response = authenticationService.validateLogin(user, password)) {
                    is ApiResult.Success<*> -> {
                        saveUser(response.data!! as Login, context)
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

    private fun saveUser(login: Login, context: Context) {
        viewModelScope.launch {
            val database = AppDataBase.getDatabase(context)
            val userRepository = UserDbDataSource(database)
            val menuRepository = MenuDbDataSource(database)
            val alertRepository = AlertDbDataSource(database)

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
            _user.postValue(userRepository.getUser(login.usuarioId).toUser())
        }
    }
}