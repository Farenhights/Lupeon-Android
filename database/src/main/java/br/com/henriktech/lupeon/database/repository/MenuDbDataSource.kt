package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.database.db.AppDataBase
import br.com.henriktech.lupeon.database.db.MenuEntity
import kotlinx.coroutines.flow.Flow

class MenuDbDataSource(private val database: AppDataBase) : MenuRepository {

    override suspend fun deleteAll() = database.alertDao().deleteAll()

    override suspend fun loadMenus(userId: Int): List<MenuEntity> = database.menuDao().loadMenus(userId)

    override suspend fun createListMenu(listMenuEntity: List<MenuEntity>) = database.menuDao().saveAll(listMenuEntity)

}
