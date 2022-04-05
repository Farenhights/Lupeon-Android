package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class OccurrenceFilterList(
    val data: List<OccurrenceFilter>,
    val status: String
): Parcelable

fun OccurrenceFilterList.toArraylistNames(): ArrayList<String> {
    val arrayList = arrayListOf<String>()
    data.forEach {
        arrayList.add(it.Descricao)
    }
    return arrayList
}