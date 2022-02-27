package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PeriodFilter(
    @SerializedName("PeriodoId")
    val periodId: Int,
    @SerializedName("Descricao")
    val description: String
) : Parcelable