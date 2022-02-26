package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransporterFilterList(
    @SerializedName("data")
    val transporterFilterList: List<TransporterFilter>,
    val status: String
) : Parcelable

fun TransporterFilterList.toArrylistNames(): ArrayList<String> {
    val arrayList = arrayListOf<String>()
    transporterFilterList.forEach {
        arrayList.add(it.name.toUpperCase())
    }
    return arrayList
}