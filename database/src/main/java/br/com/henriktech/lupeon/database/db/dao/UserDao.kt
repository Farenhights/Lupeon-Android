package br.com.henriktech.lupeon.database.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.henriktech.lupeon.database.db.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(userEntity: UserEntity)

    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(): Flow<UserEntity>

}