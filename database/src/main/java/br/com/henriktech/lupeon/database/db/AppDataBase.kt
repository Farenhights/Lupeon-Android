package br.com.henriktech.lupeon.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.henriktech.lupeon.database.db.dao.AlertDao
import br.com.henriktech.lupeon.database.db.dao.MenuDao
import br.com.henriktech.lupeon.database.db.dao.UserDao

@Database(entities = [
    UserEntity::class,
    AlertEntity::class,
    MenuEntity::class
], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun alertDao(): AlertDao
    abstract fun menuDao(): MenuDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "lupeon_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}