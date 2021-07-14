package br.com.henriktech.lupeon.ui.login.presentention

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.henriktech.lupeon.data.service.AppInfoService

class PresentationViewModel(private val service : AppInfoService): ViewModel() {

    val versionLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val contactsLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getInformations() {
        versionLiveData.postValue(service.getVersion())
        contactsLiveData.postValue(service.getContacts())
    }
}