package br.com.henriktech.lupeon.ui.profile.menu

import androidx.lifecycle.*
import br.com.henriktech.lupeon.api.model.Alerta
import br.com.henriktech.lupeon.data.model.*
import br.com.henriktech.lupeon.database.repository.AlertRepository
import br.com.henriktech.lupeon.database.repository.MenuRepository
import br.com.henriktech.lupeon.database.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileMenuViewModel(
    private val userRepository: UserRepository,
    private val menuRepository: MenuRepository,
    private val alertRepository: AlertRepository
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
        viewModelScope.launch {
            userRepository.getUser().let {
                _user.postValue(it.toUser())
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.delete(_user.value!!.toUserEntity())
        }
    }
}