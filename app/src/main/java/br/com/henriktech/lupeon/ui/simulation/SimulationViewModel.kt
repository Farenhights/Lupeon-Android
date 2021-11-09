package br.com.henriktech.lupeon.ui.simulation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.henriktech.lupeon.data.model.User
import br.com.henriktech.lupeon.database.repository.UserRepository
import kotlinx.coroutines.launch

class SimulationViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: MutableLiveData<User?> = _user

    fun logout() {
        viewModelScope.launch {
            userRepository.delete()
            _user.postValue(null)
        }
    }
}