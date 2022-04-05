package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class InvoiceList(
    @SerializedName("data")
    val invoiceList: List<Invoice>,
    val status: String,
) : Parcelable
