package br.com.henriktech.lupeon.database.repository

import br.com.henriktech.lupeon.database.db.AlertEntity

interface AlertRepository {

    fun createAlert(alertEntity: AlertEntity)

    fun deleteAll()
}