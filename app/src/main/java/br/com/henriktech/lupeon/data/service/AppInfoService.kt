package br.com.henriktech.lupeon.data.service

import androidx.multidex.BuildConfig.VERSION_NAME
import org.koin.android.BuildConfig


open class AppInfoService {

    private val version = "${BuildConfig.VERSION_NAME}.${BuildConfig.VERSION_CODE}"

    private val contacts = "ATENDIMENTO<br>(11) 3197 8140<br>Seg. à sex. das 8 às 18h." +
            "<br>ouvidoria@lupeon.com.br"

    fun getVersion() = version
    fun getContacts() = contacts
}