package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.database.db.AlertEntity

interface AlertRepository {

    suspend fun createListAlert(listAlertEntity: List<AlertEntity>)

    suspend fun deleteAll()

    suspend fun loadAlerts(userId: Int): List<AlertEntity>
}