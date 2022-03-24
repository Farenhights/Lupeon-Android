package br.com.henriktech.lupeon.ui.driver

import android.text.Html
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.api.model.response.Indicadores
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.model.*
import br.com.henriktech.lupeon.data.service.IndicatorsService
import br.com.henriktech.lupeon.database.repository.MenuRepository
import br.com.henriktech.lupeon.database.repository.UserRepository
import kotlinx.coroutines.launch

class DriverViewModel(
    private val userRepository: UserRepository,
    private val menuRepository: MenuRepository,
    private val indicatorsService: IndicatorsService,
) : ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: MutableLiveData<User?> = _user

    private val _menus = MutableLiveData<List<Menu>>()
    val menus: LiveData<List<Menu>> = _menus

    private val _indicators = MutableLiveData<List<Indicator>>()
    val indicators: LiveData<List<Indicator>> = _indicators

    private val _errorMessage = MutableLiveData<String>()

    init {
        _user.observeForever { user ->
            if (user != null) {
                viewModelScope.launch {
                    val ocorrencia = Html.fromHtml("Nova<br/>Ocorrência", 0).toString()
                    val notas = Html.fromHtml("Notas em<br/>trânsito", 0).toString()
                    val menus = arrayListOf<Menu>()
                    menus.add(Menu(option = ocorrencia,
                        true))
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