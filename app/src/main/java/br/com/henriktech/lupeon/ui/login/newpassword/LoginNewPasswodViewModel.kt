package br.com.henriktech.lupeon.ui.login.newpassword

import android.app.Application
import android.text.Html
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.henriktech.lupeon.R

class LoginNewPasswodViewModel(application:Application): AndroidViewModel(application) {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    init {
        _title.postValue(
            Html.fromHtml(application.getString(R.string.new_password), 0).toString()
        )
    }
}