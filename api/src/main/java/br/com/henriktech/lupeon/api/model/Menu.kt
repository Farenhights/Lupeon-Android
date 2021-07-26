package br.com.henriktech.lupeon.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Menu(
    val option: String,
    val visible: Boolean
): Parcelable