package br.com.henriktech.lupeon.ui.invoice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.api.model.response.Invoice
import br.com.henriktech.lupeon.api.model.response.InvoiceList
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.model.User
import br.com.henriktech.lupeon.data.model.toUser
import br.com.henriktech.lupeon.data.service.InvoiceService
import br.com.henriktech.lupeon.database.repository.UserRepository
import kotlinx.coroutines.launch

class InvoiceViewModel(
    private val userRepository: UserRepository,
    private val service: InvoiceService
) : ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: MutableLiveData<User?> = _user

    private val _invoiceList = MutableLiveData<ArrayList<Invoice>>()
    val invoiceList: MutableLiveData<ArrayList<Invoice>> = _invoiceList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: MutableLiveData<String> = _errorMessage

    fun getUser() {
        viewModelScope.launch {
            userRepository.getUser().let {
                _user.postValue(it.toUser())
            }
        }
    }

    fun getInvoices(
        token: String,
        companyId: Int,
        dataInicio: String,
        dataFim: String,
        destinatarioId: Int,
        periodoId: Int,
        status: Int,
    ) {
        viewModelScope.launch {
            when (val response = service.getInvoiceDriver(
                token, companyId, dataInicio, dataFim,
                destinatarioId, periodoId, status
            )) {
                is ApiResult.Success<*> -> {
                    val invoiceData = response.data!! as InvoiceList
                    _invoiceList.postValue(invoiceData.invoiceList as ArrayList<Invoice>)
                }
                is ApiResult.Error -> {
                    _errorMessage.postValue(response.message)
                }
            }
        }
    }
}