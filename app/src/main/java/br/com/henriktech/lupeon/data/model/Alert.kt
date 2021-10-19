package br.com.henriktech.lupeon.data.model

import br.com.henriktech.lupeon.api.model.response.Alerta
import br.com.henriktech.lupeon.database.db.AlertEntity

data class Alert(
    val icon: String,
    val link: String,
    val text: String,
    val title: String
)

fun Alerta.toAlertEntity(id: Int): AlertEntity {
    return with(this) {
        AlertEntity(
            userId = id,
            icon = this.icon,
            link = this.link,
            text = this.texto,
            title = this.titulo
        )
    }
}

fun AlertEntity.toAlert(): Alert {
    return with(this) {
        Alert(
            icon = this.icon,
            link = this.link,
            text = this.text,
            title = this.title
        )
    }
}

fun List<AlertEntity>.toListAlert(): List<Alert> {
    val arrayList = ArrayList<Alert>()
    with(this) {
        forEach {
            arrayList.add(it.toAlert())
        }
    }
    return arrayList
}