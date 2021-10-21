package br.com.henriktech.lupeon.data.model

import br.com.henriktech.lupeon.api.model.response.Indicador

data class Indicator(
    val description: String,
    val value: String
)

fun Indicador.toIndicator(): Indicator {
    return with(this) {
        Indicator(
            description = this.Indicador,
            value = this.Valor
        )
    }
}

fun List<Indicador>.toIndicatorList(): List<Indicator> {
    val arrayList = ArrayList<Indicator>()
    with(this) {
        forEach {
            arrayList.add(it.toIndicator())
        }
    }
    return arrayList
}

