package com.chocomiruku.catsfacts.repository

import com.chocomiruku.catsfacts.domain.Fact
import com.chocomiruku.catsfacts.network.FactsApi
import com.chocomiruku.catsfacts.network.asDomainModel

class FactsRepository() {

    suspend fun refreshFacts() : List<Fact> {
        return FactsApi.retrofitService.getFactsAsync().await().asDomainModel()
    }
}