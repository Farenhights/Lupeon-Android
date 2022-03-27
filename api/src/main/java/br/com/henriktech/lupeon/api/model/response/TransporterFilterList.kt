package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class TransporterFilterList(
    @SerializedName("data")
    val transporterFilterList: List<TransporterFilter>,
    val status: String,
) : Parcelable

fun TransporterFilterList.toArraylistNames(): ArrayList<String> {
    val arrayList = arrayListOf<String>()
    transporterFilterList.forEach {
        arrayList.add(it.name.uppercase(Locale.ROOT))
    }
    return arrayList
}