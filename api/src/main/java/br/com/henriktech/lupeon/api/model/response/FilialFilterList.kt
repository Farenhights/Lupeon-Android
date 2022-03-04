package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class FilialFilterList(
    @SerializedName("data")
    val filialFilterList: List<FilialFilter>,
    val status: String,
) : Parcelable

fun FilialFilterList.toArrylistNames(): ArrayList<String> {
    val arrayList = arrayListOf<String>()
    filialFilterList.forEach {
        arrayList.add(it.name.uppercase(Locale.ROOT))
    }
    return arrayList
}