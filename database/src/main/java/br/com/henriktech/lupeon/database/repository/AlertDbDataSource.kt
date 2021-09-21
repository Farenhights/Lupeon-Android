package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.database.db.AlertEntity
import br.com.henriktech.lupeon.database.db.AppDataBase

class AlertDbDataSource(private val database: AppDataBase) : AlertRepository {

    override fun deleteAll() = database.alertDao().deleteAll()

    override fun createAlert(alertEntity: AlertEntity) = database.alertDao().save(alertEntity)

}
