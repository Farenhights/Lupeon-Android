package br.com.henriktech.lupeon.ui.menu

import android.text.Html
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.data.model.*
import br.com.henriktech.lupeon.data.service.AppInfoService
import br.com.henriktech.lupeon.database.repository.AlertRepository
import br.com.henriktech.lupeon.database.repository.MenuRepository
import br.com.henriktech.lupeon.database.repository.UserRepository
import kotlinx.coroutines.launch

class MenuViewModel(
    private val userRepository: UserRepository,
    private val menuRepository: MenuRepository,
    private val alertRepository: AlertRepository,
    private val informationService: AppInfoService
) : ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: MutableLiveData<User?> = _user

    private val _menus = MutableLiveData<List<Menu>>()
    val menus: LiveData<List<Menu>> = _menus

    private val _alerts = MutableLiveData<List<Alert>>()
    val alerts: LiveData<List<Alert>> = _alerts

    init {
        _user.observeForever { user ->
            if (user != null) {
                viewModelScope.launch {
                    menuRepository.loadMenus(user.userId).let {
                        _menus.postValue(it.toListMenu())
                    }
                    alertRepository.loadAlerts(user.userId).let {
                        _alerts.postValue(it.toListAlert())
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

    fun getInfoService() = Html.fromHtml(informationService.getService(), 0).toString()
}