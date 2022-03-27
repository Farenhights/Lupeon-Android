package br.com.henriktech.lupeon.ui.tracking.delivery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.api.model.response.NFe
import br.com.henriktech.lupeon.api.model.response.NFeList
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.model.User
import br.com.henriktech.lupeon.data.model.toUser
import br.com.henriktech.lupeon.data.service.TrackingService
import br.com.henriktech.lupeon.database.repository.UserRepository
import kotlinx.coroutines.launch

class DeliveryViewModel(
    private val userRepository: UserRepository,
    private val service: TrackingService,
) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: MutableLiveData<User?> = _user

    private val _invoice = MutableLiveData<NFe>()
    val invoice: MutableLiveData<NFe> = _invoice

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: MutableLiveData<String> = _errorMessage

    private val _numeroNota = MutableLiveData<Int>()

    private val _cnpj = MutableLiveData<String>()

    private val _searchFinish = MutableLiveData<Boolean>()

    init {
        _user.observeForever { user ->
            viewModelScope.launch {
                val token = user!!.tokenType
                val companyId = Integer.parseInt(user.companyId)
                val numberInvoice = _numeroNota.value!!
                val cnpj = _cnpj.value!!
                when (val response = service.getInvoice(token, companyId, numberInvoice, cnpj)) {
                    is ApiResult.Success<*> -> {
                        val invoiceList = response.data!! as NFeList
                        if(invoiceList.nFesList.isNotEmpty()) {
                            _invoice.postValue(invoiceList.nFesList.first())
                            _searchFinish.postValue(true)
                        } else {
                            _searchFinish.postValue(true)
                            _errorMessage.postValue("Nota fical não encontrada!")
                        }
                    }
                    is ApiResult.Error -> {
                        _errorMessage.postValue(response.message)
                        _searchFinish.postValue(true)
                    }
                }
            }
        }
    }

    fun getInvoice(numberInvoice: Int, cpnj: String) {
        _numeroNota.postValue(numberInvoice)
        _cnpj.postValue(cpnj)
        viewModelScope.launch {
            userRepository.getUser().let {
                _user.postValue(it.toUser())
            }
        }
    }
}