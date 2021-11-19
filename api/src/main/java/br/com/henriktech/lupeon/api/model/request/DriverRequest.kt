package br.com.henriktech.lupeon.api.model.request

import com.google.gson.annotations.SerializedName

data class DriverRequest(
    @SerializedName("MotoristaId")
    val driverId: Int,
    @SerializedName("PeriodoId")
    val timeCourseId: Int
)