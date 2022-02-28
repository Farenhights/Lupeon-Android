package br.com.henriktech.lupeon.ui.tracking.delivery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.henriktech.lupeon.data.model.User
import br.com.henriktech.lupeon.database.repository.UserRepository

class DeliveryViewModel(private val userRepository: UserRepository) : ViewModel() {

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
}