package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.database.db.MenuEntity

interface MenuRepository {

    suspend fun createListMenu(listMenuEntity: List<MenuEntity>)

    suspend fun deleteAll()
}