package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.database.db.AlertEntity

interface AlertRepository {

    suspend fun createAlert(alertEntity: AlertEntity)

    suspend fun deleteAll()
}