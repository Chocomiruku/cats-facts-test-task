package com.chocomiruku.catsfacts.overview

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocomiruku.catsfacts.database.getDatabase
import com.chocomiruku.catsfacts.domain.Fact
import com.chocomiruku.catsfacts.repository.FactsRepository
import com.chocomiruku.catsfacts.util.ApiStatus
import kotlinx.coroutines.launch

class FactsOverviewViewModel(application: Application) : ViewModel() {

    private val database = getDatabase(application)
    private val factsRepository = FactsRepository(database)

    private val _status = MutableLiveData<ApiStatus>()

    val status: LiveData<ApiStatus>
        get() = _status

    private val _facts = MutableLiveData<List<Fact>?>()

    val facts: LiveData<List<Fact>?>
        get() = _facts

    private val _navigateToSelectedFact = MutableLiveData<Fact?>()

    val navigateToSelectedFact: MutableLiveData<Fact?>
        get() = _navigateToSelectedFact


    init {
        getFacts()
    }

    private fun getFacts() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _facts.value = factsRepository.refreshFacts()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _facts.value = ArrayList()
            }
        }
    }

    fun displayFactDetails(fact: Fact) {
        _navigateToSelectedFact.value = fact
    }

    fun displayFactDetailsComplete() {
        _navigateToSelectedFact.value = null
    }
}