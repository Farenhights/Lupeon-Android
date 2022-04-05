package br.com.henriktech.lupeon.api.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OccurrenceFilter(
    val Descricao: String,
    val Id: Int
) : Parcelable