package br.com.henriktech.lupeon.ui.base

import br.com.henriktech.lupeon.api.model.response.Ocorrencia

interface OnOccurrenceClickListener {
    fun onOccurrenceClicked(occurrence: Ocorrencia)
}