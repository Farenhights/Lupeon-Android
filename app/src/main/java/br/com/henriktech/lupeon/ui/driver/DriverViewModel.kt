package br.com.henriktech.lupeon.ui.driver

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.data.model.Menu
import br.com.henriktech.lupeon.data.model.User
import br.com.henriktech.lupeon.data.model.toListMenu
import br.com.henriktech.lupeon.data.model.toUser
import br.com.henriktech.lupeon.database.repository.MenuRepository
import br.com.henriktech.lupeon.database.repository.UserRepository
import kotlinx.coroutines.launch

class DriverViewModel(
    private val userRepository: UserRepository,
    private val menuRepository: MenuRepository,
) : ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: MutableLiveData<User?> = _user

    private val _menus = MutableLiveData<List<Menu>>()
    val menus: LiveData<List<Menu>> = _menus

    init {
        _user.observeForever { user ->
            if (user != null) {
                viewModelScope.launch {
                    menuRepository.loadMenus(user.userId).let {
                        _menus.postValue(it.toListMenu())
                    }
                }
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
            userRepository.delete()
            _user.postValue(null)
        }
    }
}