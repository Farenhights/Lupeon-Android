package br.com.henriktech.lupeon.database.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.henriktech.lupeon.database.db.MenuEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(listMenuEntity: List<MenuEntity>)

    @Query("DELETE FROM menu")
    suspend fun deleteAll()

    @Query("SELECT * FROM menu WHERE userId = :userId")
    fun loadMenus(userId: Int): Flow<List<MenuEntity>>
}