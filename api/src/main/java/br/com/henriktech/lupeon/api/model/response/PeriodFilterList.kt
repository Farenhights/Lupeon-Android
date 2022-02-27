package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
data class PeriodFilterList(
    @SerializedName("data")
    val periodFilterList: List<PeriodFilter>,
    val status: String
) : Parcelable

fun PeriodFilterList.toArrylistDescriptions(): ArrayList<String> {
    val arrayList = arrayListOf<String>()
    periodFilterList.forEach {
        arrayList.add(it.description.uppercase(Locale.ROOT))
    }
    return arrayList
}