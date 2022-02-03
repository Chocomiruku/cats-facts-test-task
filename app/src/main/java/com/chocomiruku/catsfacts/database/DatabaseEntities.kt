package com.chocomiruku.catsfacts.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chocomiruku.catsfacts.domain.Fact

@Entity
data class DatabaseFact constructor(
    @PrimaryKey
    val id: String,
    val text: String)

fun DatabaseFact.asDomainModel() = Fact(
    id = id,
    text = text
)