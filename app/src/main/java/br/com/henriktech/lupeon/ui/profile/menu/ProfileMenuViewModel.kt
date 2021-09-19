package br.com.henriktech.lupeon.ui.profile.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileMenuViewModel : ViewModel() {

    val name: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val menus: MutableLiveData<List<Menu>> by lazy {
        MutableLiveData<List<Menu>>()
    }

    val alerts: MutableLiveData<List<Alerta>> by lazy {
        MutableLiveData<List<Alerta>>()
    }

    fun setLogin(login: Login) {
        menus.postValue(login.Menus)
        alerts.postValue(login.Alertas)

        when(login.TipoUsuario) {
            "E" -> name.postValue("Embarcador")
            "T" -> name.postValue("Transportador")
            else -> name.postValue("Motorista")
        }

    }
}