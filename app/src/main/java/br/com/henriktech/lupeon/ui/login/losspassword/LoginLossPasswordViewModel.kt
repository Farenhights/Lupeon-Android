package br.com.henriktech.lupeon.ui.login.losspassword

import android.app.Application
import android.text.Html
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.service.AuthenticationService
import br.com.henriktech.lupeon.util.EmailUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginLossPasswordViewModel(
    application: Application,
    private val authenticationService: AuthenticationService
) : AndroidViewModel(application) {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _lossPassword = MutableLiveData<String>()
    val lossPassword: LiveData<String> get() = _lossPassword

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    init {
        _title.postValue(
            Html.fromHtml(application.getString(R.string.loss_my_password), 0).toString()
        )
        _lossPassword.postValue(
            Html.fromHtml(application.getString(R.string.text_loss_password), 0).toString()
        )
    }

    fun sendEmail(email: String) {
        if (EmailUtil.isValidEmail(email)) {
            viewModelScope.launch(context = Dispatchers.IO) {
                when (val response = authenticationService.sendEmail(email)) {
                    is ApiResult.Success<*> -> {
                        _message.postValue("Código enviado com sucesso!")
                    }
                    is ApiResult.Error -> {
                        _message.postValue(response.message)
                    }
                }
            }
        } else {
            _message.postValue("E-mail inválido!")
        }
    }
}
