package br.com.henriktech.lupeon.ui.driver

import android.graphics.Bitmap
import android.text.Html
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.api.model.response.Indicadores
import br.com.henriktech.lupeon.api.model.response.OccurrenceFilterList
import br.com.henriktech.lupeon.api.model.response.toArraylistNames
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.model.*
import br.com.henriktech.lupeon.data.service.FilterService
import br.com.henriktech.lupeon.data.service.IndicatorsService
import br.com.henriktech.lupeon.database.repository.UserRepository
import br.com.henriktech.lupeon.ui.driver.DialogClick.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DriverViewModel(
    private val userRepository: UserRepository,
    private val indicatorsService: IndicatorsService,
    private val filterService: FilterService,
) : ViewModel() {
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO

    private val _user = MutableLiveData<User?>()
    val user: MutableLiveData<User?> = _user

    private val _menus = MutableLiveData<List<Menu>>()
    val menus: LiveData<List<Menu>> = _menus

    private val _indicators = MutableLiveData<List<Indicator>>()
    val indicators: LiveData<List<Indicator>> = _indicators

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

    private val _picture = MutableLiveData<Bitmap>()

    private val _confirmedMessage = MutableLiveData<String>()
    val confirmedMessage: LiveData<String> = _confirmedMessage

    private val _occurrenceList = MutableLiveData<ArrayList<String>>()
    val occurrenceList: LiveData<ArrayList<String>> = _occurrenceList

    private val _errorMessage = MutableLiveData<String>()

    init {
        _user.observeForever { user ->
            if (user != null) {
                viewModelScope.launch {
                    val ocorrencia = Html.fromHtml("Nova<br/>Ocorrência", 0).toString()
                    val notas = Html.fromHtml("Notas em<br/>trânsito", 0).toString()
                    val menus = arrayListOf<Menu>()
                    menus.add(
                        Menu(
                            option = ocorrencia,
                            true
                        )
                    )
                    menus.add(Menu(option = notas, true))
                    _menus.postValue(menus as List<Menu>)
                }
                viewModelScope.launch {
                    when (val response =
                        indicatorsService.getDriverIndicators(
                            user.tokenType,
                            user.userId,
                            user.companyId.toInt(),
                            0
                        )
                    ) {
                        is ApiResult.Success<*> -> {
                            val indicators = response.data!! as Indicadores
                            _indicators.postValue(indicators.indicadores.toIndicatorList())
                        }
                        is ApiResult.Error -> {
                            _errorMessage.postValue(response.message)
                        }
                    }
                }
            }
        }

        _picture.observeForever {

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

    fun dialogClick(click: DialogClick) = when (click) {
        OCCURRENCE_OPEN -> _dialogOccurrence.postValue(View.VISIBLE)
        OCCURRENCE_CLOSE -> _dialogOccurrence.postValue(View.GONE)
        INVOICE_OPEN -> _dialogInvoice.postValue(View.VISIBLE)
        INVOICE_CLOSE -> _dialogInvoice.postValue(View.GONE)
        DELIVERED_OPEN -> _dialogDelivered.postValue(View.VISIBLE)
        DELIVERED_CLOSE -> _dialogDelivered.postValue(View.GONE)
        TYPE_OPEN -> _dialogType.postValue(View.VISIBLE)
        TYPE_CLOSE -> _dialogType.postValue(View.GONE)
        CONFIRMED_OPEN -> _dialogConfirmed.postValue(View.VISIBLE)
        CONFIRMED_CLOSE -> _dialogConfirmed.postValue(View.GONE)
        else -> {}
    }

    fun setPicture(bitmap: Bitmap) {
        _picture.postValue(bitmap)
    }

    fun setConfirmedMessage(message: String) {
        _confirmedMessage.postValue(message)
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