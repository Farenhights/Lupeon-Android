package br.com.henriktech.lupeon.ui.invoice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.api.model.response.NFeList
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.model.User
import br.com.henriktech.lupeon.data.service.InvoiceService
import br.com.henriktech.lupeon.database.repository.UserRepository
import kotlinx.coroutines.launch

class InvoiceViewModel(
    private val userRepository: UserRepository,
    private val service: InvoiceService
) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: MutableLiveData<User?> = _user

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: MutableLiveData<String> = _errorMessage

    init {
        _user.observeForever { user ->
            viewModelScope.launch {
                val token = user!!.tokenType
                val companyId = Integer.parseInt(user.companyId)
                when (val response = service.getInvoice(token, companyId)) {
                    is ApiResult.Success<*> -> {
                        val invoiceList = response.data!! as NFeList
                        if (invoiceList.nFesList.isNotEmpty()) {
                            //  _occurrenceList.postValue(invoiceList.nFesList.first().Ocorrencias as ArrayList<Ocorrencia>)
                        } else {
                            _errorMessage.postValue("Nota fical não encontrada!")
                        }
                    }
                    is ApiResult.Error -> {
                        _errorMessage.postValue(response.message)
                    }
                }
            }
        }
    }
}