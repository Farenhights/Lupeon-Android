package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilialFilter(
    @SerializedName("EmpresaId")
    val companyId: Int,
    @SerializedName("CNPJFilial")
    val cnpj: String,
    @SerializedName("NomeFilial")
    val name: String,
) : Parcelable