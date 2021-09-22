package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.database.db.AppDataBase
import br.com.henriktech.lupeon.database.db.MenuEntity

class MenuDbDataSource(private val database: AppDataBase) : MenuRepository {

    override suspend fun deleteAll() = database.alertDao().deleteAll()

    override suspend fun createListMenu(listMenuEntity: List<MenuEntity>) = database.menuDao().saveAll(listMenuEntity)

}
