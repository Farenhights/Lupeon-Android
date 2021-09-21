package br.com.henriktech.lupeon.database.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.henriktech.lupeon.database.db.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(userEntity: UserEntity)

    @Query("SELECT * FROM user WHERE userId = :userId")
    fun getUser(userId: Int): UserEntity
}