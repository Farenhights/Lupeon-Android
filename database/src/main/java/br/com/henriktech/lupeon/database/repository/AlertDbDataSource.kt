package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.database.db.AlertEntity
import br.com.henriktech.lupeon.database.db.AppDataBase

class AlertDbDataSource(private val database: AppDataBase) : AlertRepository {

    override suspend fun deleteAll() = database.alertDao().deleteAll()

    override suspend fun createListAlert(listAlertEntity: List<AlertEntity>) = database.alertDao().saveAll(listAlertEntity)

}
