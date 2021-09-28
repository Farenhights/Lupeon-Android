package br.com.henriktech.lupeon.ui.profile.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.henriktech.lupeon.api.model.Alerta
import br.com.henriktech.lupeon.data.model.Menu
import br.com.henriktech.lupeon.data.model.User
import br.com.henriktech.lupeon.data.model.toListMenu
import br.com.henriktech.lupeon.data.model.toUser
import br.com.henriktech.lupeon.database.repository.AlertRepository
import br.com.henriktech.lupeon.database.repository.MenuRepository
import br.com.henriktech.lupeon.database.repository.UserRepository

class ProfileMenuViewModel(
    val userRepository: UserRepository,
    val menuRepository: MenuRepository,
    val alertRepository: AlertRepository
) : ViewModel() {


    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _menus = MutableLiveData<List<Menu>>()
    val menus: LiveData<List<Menu>> = _menus

    val alerts: MutableLiveData<List<Alerta>> by lazy {
        MutableLiveData<List<Alerta>>()
    }


    init {
        _user.value?.let { user ->
            menuRepository.loadMenus(user.userId).asLiveData().value!!.let {
                _menus.postValue(it.toListMenu())
            }
        }
    }

    fun getUser() {
        userRepository.getUser().asLiveData().value!!.let {
            _user.postValue(it.toUser())
        }
    }
}