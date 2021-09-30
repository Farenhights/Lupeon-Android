package br.com.henriktech.lupeon.database.db.dao

import androidx.room.*
import br.com.henriktech.lupeon.database.db.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(userEntity: UserEntity)

    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(): UserEntity

    @Delete()
    fun delete(userEntity: UserEntity)

}