package br.com.henriktech.lupeon.ui.profile.indicators

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.henriktech.lupeon.data.model.Indicator

class ProfileIndicatorsViewModel: ViewModel() {

    private val _indicators = MutableLiveData<List<Indicator>>()
    val indicators: LiveData<List<Indicator>> = _indicators

}