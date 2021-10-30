package br.com.henriktech.lupeon.ui.profile.indicators

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.api.model.response.Indicadores
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.model.Indicator
import br.com.henriktech.lupeon.data.model.User
import br.com.henriktech.lupeon.data.model.toIndicatorList
import br.com.henriktech.lupeon.data.model.toUser
import br.com.henriktech.lupeon.data.service.IndicatorsService
import br.com.henriktech.lupeon.database.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileIndicatorsViewModel(
    private val userRepository: UserRepository,
    private val indicatorsService: IndicatorsService
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _indicators = MutableLiveData<List<Indicator>>()
    val indicators: LiveData<List<Indicator>> = _indicators

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        _user.observeForever { user ->
            if (user != null) {
                viewModelScope.launch {
                    when (val response =
                        indicatorsService.getShipperIndicators(user.tokenType, user.userId, 0)) {
                        is ApiResult.Success<*> -> {
                            val indicadores = response.data!! as Indicadores
                            _indicators.postValue(indicadores.indicadores.toIndicatorList())
                        }
                        is ApiResult.Error -> {
                            _errorMessage.postValue(response.message)
                        }
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