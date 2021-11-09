package br.com.henriktech.lupeon.data.service

import br.com.henriktech.lupeon.BuildConfig

open class AppInfoService {

    private val version = "${BuildConfig.VERSION_NAME}.${BuildConfig.VERSION_CODE}"

    private val service = "ATENDIMENTO<br>(11) 3197 8140<br>Seg. à sex. das 8 às 18h."


    private val email = "ouvidoria@lupeon.com.br"

    fun getVersion() = version
    fun getContacts() = "$service<br>$email"
    fun getService() = service
}