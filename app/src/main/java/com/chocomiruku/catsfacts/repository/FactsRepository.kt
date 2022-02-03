package com.chocomiruku.catsfacts.repository

import android.util.Log
import com.chocomiruku.catsfacts.database.DatabaseFact
import com.chocomiruku.catsfacts.database.FactsDatabase
import com.chocomiruku.catsfacts.database.asDomainModel
import com.chocomiruku.catsfacts.domain.Fact
import com.chocomiruku.catsfacts.domain.asDatabaseModel
import com.chocomiruku.catsfacts.network.FactsApi
import com.chocomiruku.catsfacts.network.PhotosApi
import com.chocomiruku.catsfacts.network.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FactsRepository(private val database: FactsDatabase) {

    suspend fun refreshFacts() : List<Fact> {
        return FactsApi.retrofitService.getFactsAsync().await().asDomainModel()
    }

    suspend fun getCatPhotoUrl() : String {
        return PhotosApi.retrofitService.getRandomPhotoAsync().await().imgSrcUrl
    }

    suspend fun addToFavourites(fact: Fact) {
        withContext(Dispatchers.IO) {
            database.factDao.insert(fact.asDatabaseModel())
        }
    }

    suspend fun deleteFromFavourites(fact: Fact) {
        withContext(Dispatchers.IO) {
            database.factDao.delete(fact.asDatabaseModel())
        }
    }

    suspend fun checkIfIsAddedToFavourites(fact: Fact) : Boolean {
        var factInFavourites: DatabaseFact?
        withContext(Dispatchers.IO) {
            factInFavourites = database.factDao.getFactFromFavourites(fact.id)
        }
        return factInFavourites != null
    }
}