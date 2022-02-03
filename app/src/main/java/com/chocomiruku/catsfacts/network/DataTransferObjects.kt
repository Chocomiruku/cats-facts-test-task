package com.chocomiruku.catsfacts.network

import com.chocomiruku.catsfacts.domain.Fact
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class NetworkFact(
    val status: Status,
    @Json(name = "_id") val id: String,
    val user: String,
    val text: String,
    @Json(name = "__v") val v: Int,
    val source: String,
    val updatedAt: String,
    val type: String,
    val createdAt: String,
    val deleted: Boolean,
    val used: Boolean
)

@JsonClass(generateAdapter = true)
data class Status(
    val verified: Boolean,
    val sentCount: Int
)

@JsonClass(generateAdapter = true)
data class Photo(
    @Json(name = "file") val imgSrcUrl: String
)


fun List<NetworkFact>.asDomainModel(): List<Fact> {
    return map {
        Fact(
            id = it.id,
            text = it.text
        )
    }
}