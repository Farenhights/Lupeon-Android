package br.com.henriktech.lupeon.ui.menu

import android.graphics.Bitmap
import android.text.Html
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.api.model.response.OccurrenceFilterList
import br.com.henriktech.lupeon.api.model.response.toArraylistNames
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.model.*
import br.com.henriktech.lupeon.data.service.AppInfoService
import br.com.henriktech.lupeon.data.service.FilterService
import br.com.henriktech.lupeon.database.repository.AlertRepository
import br.com.henriktech.lupeon.database.repository.MenuRepository
import br.com.henriktech.lupeon.database.repository.UserRepository
import br.com.henriktech.lupeon.ui.driver.DialogClick
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuViewModel(
    private val userRepository: UserRepository,
    private val menuRepository: MenuRepository,
    private val alertRepository: AlertRepository,
    private val informationService: AppInfoService,
    private val filterService: FilterService,
) : ViewModel() {
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO

    private val _user = MutableLiveData<User?>()
    val user: MutableLiveData<User?> = _user

    private val _menus = MutableLiveData<List<Menu>>()
    val menus: LiveData<List<Menu>> = _menus

    private val _alerts = MutableLiveData<List<Alert>>()
    val alerts: LiveData<List<Alert>> = _alerts

    private val _dialogOccurrence = MutableLiveData<Int>()
    val dialogOccurrence: LiveData<Int> = _dialogOccurrence

    private val _dialogInvoice = MutableLiveData<Int>()
    val dialogInvoice: LiveData<Int> = _dialogInvoice

    private val _dialogDelivered = MutableLiveData<Int>()
    val dialogDelivered: LiveData<Int> = _dialogDelivered

    private val _dialogConfirmed = MutableLiveData<Int>()
    val dialogConfirmed: LiveData<Int> = _dialogConfirmed

    private val _dialogType = MutableLiveData<Int>()
    val dialogType: LiveData<Int> = _dialogType

    private val _occurrenceList = MutableLiveData<ArrayList<String>>()
    val occurrenceList: LiveData<ArrayList<String>> = _occurrenceList

    private val _picture = MutableLiveData<Bitmap>()

    private val _errorMessage = MutableLiveData<String>()

    private val _confirmedMessage = MutableLiveData<String>()
    val confirmedMessage: LiveData<String> = _confirmedMessage

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

    fun setPicture(bitmap: Bitmap) {
        _picture.postValue(bitmap)
    }

    fun setConfirmedMessage(message: String) {
        _confirmedMessage.postValue(message)
    }

    fun dialogClick(click: DialogClick) = when (click) {
        DialogClick.OCCURRENCE_OPEN -> _dialogOccurrence.postValue(View.VISIBLE)
        DialogClick.OCCURRENCE_CLOSE -> _dialogOccurrence.postValue(View.GONE)
        DialogClick.INVOICE_OPEN -> _dialogInvoice.postValue(View.VISIBLE)
        DialogClick.INVOICE_CLOSE -> _dialogInvoice.postValue(View.GONE)
        DialogClick.DELIVERED_OPEN -> _dialogDelivered.postValue(View.VISIBLE)
        DialogClick.DELIVERED_CLOSE -> _dialogDelivered.postValue(View.GONE)
        DialogClick.TYPE_OPEN -> _dialogType.postValue(View.VISIBLE)
        DialogClick.TYPE_CLOSE -> _dialogType.postValue(View.GONE)
        DialogClick.CONFIRMED_OPEN -> _dialogConfirmed.postValue(View.VISIBLE)
        DialogClick.CONFIRMED_CLOSE -> _dialogConfirmed.postValue(View.GONE)
    }

    fun showOccurrences() {
        viewModelScope.launch {
            withContext(coroutineDispatcher) {
                when (val response = filterService.getOccurrences(_user.value!!.tokenType)) {
                    is ApiResult.Success<*> -> {
                        val occurences = response.data!! as OccurrenceFilterList
                        _occurrenceList.postValue(occurences.toArraylistNames())
                    }
                    is ApiResult.Error -> _errorMessage.postValue(response.message)
                }
            }
        }
    }
}