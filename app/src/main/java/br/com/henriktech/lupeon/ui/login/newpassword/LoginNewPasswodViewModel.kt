package br.com.henriktech.lupeon.ui.login.newpassword

import android.app.Application
import android.text.Html
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.service.AuthenticationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginNewPasswodViewModel(
    application: Application,
    private val authenticationService: AuthenticationService
) : AndroidViewModel(application) {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    init {
        _title.postValue(
            Html.fromHtml(application.getString(R.string.new_password), 0).toString()
        )
    }

    fun confirmNewPassword(token: String, password: String, passwordConfirm: String) {
        if (password.equals(passwordConfirm)) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    when (val response =
                        authenticationService.redefinePassword(token, password, passwordConfirm)) {
                        is ApiResult.Success<*> -> {
                            _message.postValue("Senha alterada com sucesso!")
                        }
                        is ApiResult.Error -> {
                            _message.postValue(response.message)
                        }
                    }
                }
            }
        } else {
            _message.postValue("Senhas são diferentes!")
        }
    }
}