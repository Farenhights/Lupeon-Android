package br.com.henriktech.lupeon.api.model.request

import com.google.gson.annotations.SerializedName

data class TransporterRequest(
    @SerializedName("TransportadorId")
    val driverId: Int,
    @SerializedName("PeriodoId")
    val timeCourseId: Int
)