package br.com.henriktech.lupeon.ui.indicators

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.api.model.response.*
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.model.Indicator
import br.com.henriktech.lupeon.data.model.User
import br.com.henriktech.lupeon.data.model.toIndicatorList
import br.com.henriktech.lupeon.data.model.toUser
import br.com.henriktech.lupeon.data.service.FilterService
import br.com.henriktech.lupeon.data.service.IndicatorsService
import br.com.henriktech.lupeon.database.repository.UserRepository
import kotlinx.coroutines.launch
import java.util.*

class IndicatorsViewModel(
    private val userRepository: UserRepository,
    private val indicatorsService: IndicatorsService,
    private val filterService: FilterService,
) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: MutableLiveData<User?> = _user

    private val _indicators = MutableLiveData<List<Indicator>>()
    val indicators: LiveData<List<Indicator>> = _indicators

    private val _transporters = MutableLiveData<ArrayList<String>>()
    val transporters: LiveData<ArrayList<String>> = _transporters

    private val _periods = MutableLiveData<ArrayList<String>>()
    val periods: LiveData<ArrayList<String>> = _periods

    private val _indicatorsClearFilters = MutableLiveData<List<Indicator>>()

    private val _transportersList = MutableLiveData<List<TransporterFilter>>()
    private val _transporterSelectedPosition = MutableLiveData<Int>()
    private val _periodsList = MutableLiveData<List<PeriodFilter>>()
    private val _periodSelectedPosition = MutableLiveData<Int>()

    private val _errorMessage = MutableLiveData<String>()

    init {
        _user.observeForever { user ->
            if (user != null) {
                consultIndicator(0, 0)
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

    fun getTransportersFilters(token: String, companyId: Int) {
        viewModelScope.launch {
            when (val response = filterService.getTransportersFilter(token, companyId)) {
                is ApiResult.Success<*> -> {
                    val transporterFilterList = response.data!! as TransporterFilterList
                    _transporters.postValue(transporterFilterList.toArrylistNames())
                    _transportersList.postValue(transporterFilterList.transporterFilterList)
                }
                is ApiResult.Error -> {
                    _errorMessage.postValue(response.message.uppercase(Locale.ROOT))
                }
            }
        }
    }

    fun setTransportersFilter(selectedPosition: Int) {
        _transporterSelectedPosition.postValue(selectedPosition)
    }

    fun getPeriodsFilters(token: String, companyId: Int) {
        viewModelScope.launch {
            when (val response = filterService.getPeriodsFilter(token, companyId)) {
                is ApiResult.Success<*> -> {
                    val periodFilterList = response.data!! as PeriodFilterList
                    _periods.postValue(periodFilterList.toArrylistDescriptions())
                    _periodsList.postValue(periodFilterList.periodFilterList)
                }
                is ApiResult.Error -> {
                    _errorMessage.postValue(response.message.uppercase(Locale.ROOT))
                }
            }
        }
    }

    fun setPeriodsFilters(selectedPosition: Int) {
        _periodSelectedPosition.postValue(selectedPosition)
    }

    fun toClear() {
        val indicators = _indicatorsClearFilters.value
        _indicators.postValue(indicators!!)
    }

    fun toApply() {
        val shipper = _transportersList.value!![_transporterSelectedPosition.value!!]
        val period = _periodsList.value!![_periodSelectedPosition.value!!]
        _indicators.postValue(listOf())
        consultIndicator(shipper.id, period.periodId)
    }

    private fun consultIndicator(shipperId: Int, periodId: Int) {
        val user = _user.value!!
        viewModelScope.launch {
            when (val response =
                when (user.userType) {
                    "E" -> indicatorsService.getShipperIndicators(
                        user.tokenType,
                        shipperId,
                        user.companyId.toInt(),
                        periodId
                    )
                    "T" -> indicatorsService.getTransporterIndicators(
                        user.tokenType,
                        shipperId,
                        user.companyId.toInt(),
                        periodId
                    )
                    else -> indicatorsService.getDriverIndicators(
                        user.tokenType,
                        shipperId,
                        user.companyId.toInt(),
                        periodId
                    )
                }
            ) {
                is ApiResult.Success<*> -> {
                    val indicators = response.data!! as Indicadores
                    _indicators.postValue(indicators.indicadores.toIndicatorList())
                    _indicatorsClearFilters.postValue(indicators.indicadores.toIndicatorList())
                }
                is ApiResult.Error -> {
                    _errorMessage.postValue(response.message)
                }
            }
        }
    }
}

