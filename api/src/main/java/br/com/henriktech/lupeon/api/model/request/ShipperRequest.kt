package br.com.henriktech.lupeon.api.model.request

import com.google.gson.annotations.SerializedName

data class ShipperRequest(
    @SerializedName("EmbarcadorId")
    val shipperId: Int,
    @SerializedName("PeriodoId")
    val timeCourseId: Int
)