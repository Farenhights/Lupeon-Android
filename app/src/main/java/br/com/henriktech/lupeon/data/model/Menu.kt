package br.com.henriktech.lupeon.data.model

import br.com.henriktech.lupeon.database.db.MenuEntity

data class Menu(
    val option: String,
    val visible: Boolean
)

fun br.com.henriktech.lupeon.api.model.Menu.toMenuEntity(id: Int): MenuEntity {
    return with(this) {
        MenuEntity(
            userId = id,
            option = this.option,
            visible = this.visible
        )
    }
}