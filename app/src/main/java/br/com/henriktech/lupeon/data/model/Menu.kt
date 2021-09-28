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

fun MenuEntity.toMenu(): Menu {
    return with(this) {
        Menu(
            option = this.option,
            visible = this.visible
        )
    }
}

fun List<MenuEntity>.toListMenu(): List<Menu> {
    val menus = ArrayList<Menu>()
    with(this) {
        forEach {
            menus.add(it.toMenu())
        }
    }
    return menus
}