package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.database.db.MenuEntity

interface MenuRepository {

    fun createMenu(menuEntity: MenuEntity)

    fun deleteAll()
}