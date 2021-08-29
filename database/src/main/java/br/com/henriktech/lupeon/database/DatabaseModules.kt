package br.com.henriktech.lupeon.database

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.core.context.loadKoinModules

class DatabaseModules(private val context: Context) {

    private fun initRealm() {
        Realm.init(context)
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config);
    }


    fun loadModule() {
        initRealm()
        loadKoinModules(
            listOf()
        )
    }

}