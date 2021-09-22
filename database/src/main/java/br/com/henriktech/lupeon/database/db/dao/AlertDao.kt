package br.com.henriktech.lupeon.database.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.henriktech.lupeon.database.db.AlertEntity

@Dao
interface AlertDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(alertEntity: List<AlertEntity>)

    @Query("DELETE FROM alert")
    suspend fun deleteAll()
}