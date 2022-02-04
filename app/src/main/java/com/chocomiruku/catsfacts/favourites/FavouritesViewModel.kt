package com.chocomiruku.catsfacts.favourites

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chocomiruku.catsfacts.database.getDatabase
import com.chocomiruku.catsfacts.domain.Fact
import com.chocomiruku.catsfacts.repository.FactsRepository

class FavouritesViewModel(application: Application) : ViewModel() {

    private val database = getDatabase(application)
    private val factsRepository = FactsRepository(database)

    val facts = factsRepository.favouriteFacts

    private val _navigateToSelectedFact = MutableLiveData<Fact?>()

    val navigateToSelectedFact: MutableLiveData<Fact?>
        get() = _navigateToSelectedFact

    fun displayFactDetails(fact: Fact) {
        _navigateToSelectedFact.value = fact
    }

    fun displayFactDetailsComplete() {
        _navigateToSelectedFact.value = null
    }
}