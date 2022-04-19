package br.com.henriktech.lupeon.ui.occurrence

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.api.model.response.NFeList
import br.com.henriktech.lupeon.api.model.response.Ocorrencia
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.data.model.User
import br.com.henriktech.lupeon.data.model.toUser
import br.com.henriktech.lupeon.data.service.TrackingService
import br.com.henriktech.lupeon.database.repository.UserRepository
import kotlinx.coroutines.launch

class OccurrenceViewModel(
    private val userRepository: UserRepository,
    private val service: TrackingService,
) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: MutableLiveData<User?> = _user

    private val _occurrenceList = MutableLiveData<ArrayList<Ocorrencia>>()
    val occurrenceList: MutableLiveData<ArrayList<Ocorrencia>> = _occurrenceList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: MutableLiveData<String> = _errorMessage

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
                        val invoiceList = response.data!! as NFeList
                        if (invoiceList.nFesList.isNotEmpty()) {
                            _occurrenceList.postValue(invoiceList.nFesList.first().Ocorrencias as ArrayList<Ocorrencia>)
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