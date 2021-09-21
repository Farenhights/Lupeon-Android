package br.com.henriktech.lupeon.database.db.dao

import androidx.room.*
import br.com.henriktech.lupeon.database.db.AlertEntity

@Dao
interface AlertDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(alertEntity: AlertEntity)

    @Query("DELETE FROM alert")
    fun deleteAll()
}