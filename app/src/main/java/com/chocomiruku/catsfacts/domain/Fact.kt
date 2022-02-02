package com.chocomiruku.catsfacts.domain

import android.os.Parcelable
import com.chocomiruku.catsfacts.util.smartTruncate
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fact(val id: String,
                 val text: String) : Parcelable {

    val shortFactText: String
        get() = text.smartTruncate(78)
}