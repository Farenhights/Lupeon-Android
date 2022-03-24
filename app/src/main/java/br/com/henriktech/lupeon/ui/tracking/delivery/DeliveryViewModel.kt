package br.com.henriktech.lupeon.ui.tracking.delivery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _titleName = MutableLiveData<String>()
    val titleName: MutableLiveData<String> = _titleName

    private val _numberInvoice = MutableLiveData<String>()
    val numberInvoice: MutableLiveData<String> = _numberInvoice

    private val _recipient = MutableLiveData<String>()
    val recipient: MutableLiveData<String> = _recipient

    private val _issuanceDate = MutableLiveData<String>()
    val issuanceDate: MutableLiveData<String> = _issuanceDate

    private val _expirationDate = MutableLiveData<String>()
    val expirationDate: MutableLiveData<String> = _expirationDate

    private val _invoiceAmount = MutableLiveData<String>()
    val invoiceAmount: MutableLiveData<String> = _invoiceAmount

    private val _reconciledValue = MutableLiveData<String>()
    val reconciledValue: MutableLiveData<String> = _reconciledValue

    private val _errorValue = MutableLiveData<String>()
    val errorValue: MutableLiveData<String> = _errorValue

    private val _discountValue = MutableLiveData<String>()
    val discountValue: MutableLiveData<String> = _discountValue

    private val _progressNF = MutableLiveData<Boolean>()
    val progressNF: MutableLiveData<Boolean> = _progressNF

    private val _errorMessage = MutableLiveData<String>()

    private val _numeroNota = MutableLiveData<Int>()
    private val _cnpj = MutableLiveData<String>()

    init {
        _user.observeForever { user ->
            viewModelScope.launch {
                val token = user!!.tokenType
                val companyId = Integer.parseInt(user.companyId)
                val numberInvoice = _numeroNota.value!!
                val cnpj = _cnpj.value!!
                when (val response = service.getInvoice(token, companyId, numberInvoice, cnpj)) {
                    is ApiResult.Success<*> -> {
                        val invoice = response.data!!
                        print(invoice.toString())
                    }
                    is ApiResult.Error -> {
                        _errorMessage.postValue(response.message)
                    }
                }
            }
        }
    }

    fun getUser(numberInvoice: Int, cpnj: String) {
        _numeroNota.postValue(numberInvoice)
        _cnpj.postValue(cpnj)
        viewModelScope.launch {
            userRepository.getUser().let {
                _user.postValue(it.toUser())
            }
        }
    }
}