package br.com.henriktech.lupeon.ui.login.presentention

import android.text.Html
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.henriktech.lupeon.data.service.AppInfoService

class LoginPresentationViewModel(private val service: AppInfoService) : ViewModel() {

    private val _version = MutableLiveData<String>()
    val version: LiveData<String> get() = _version

    private val _contacts = MutableLiveData<String>()
    val contacts: LiveData<String> get() = _contacts

    fun getInformations() {
        _version.postValue(service.getVersion())
        _contacts.postValue(Html.fromHtml(service.getContacts(), 0).toString())
    }
}