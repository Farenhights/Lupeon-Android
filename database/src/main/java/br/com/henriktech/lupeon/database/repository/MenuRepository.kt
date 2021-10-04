package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.database.db.MenuEntity
import kotlinx.coroutines.flow.Flow

interface MenuRepository {

    suspend fun createListMenu(listMenuEntity: List<MenuEntity>)

    suspend fun deleteAll()

    suspend fun loadMenus(userId: Int): List<MenuEntity>
}