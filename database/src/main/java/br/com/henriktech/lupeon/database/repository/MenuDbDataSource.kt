package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.database.db.AppDataBase
import br.com.henriktech.lupeon.database.db.MenuEntity

class MenuDbDataSource(private val database: AppDataBase) : MenuRepository {

    override fun deleteAll() = database.alertDao().deleteAll()

    override fun createMenu(menuEntity: MenuEntity) = database.menuDao().save(menuEntity)

}
