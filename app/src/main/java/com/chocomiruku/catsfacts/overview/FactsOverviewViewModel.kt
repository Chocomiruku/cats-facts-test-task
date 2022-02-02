package com.chocomiruku.catsfacts.overview

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.chocomiruku.catsfacts.database.getDatabase
import com.chocomiruku.catsfacts.domain.Fact
import com.chocomiruku.catsfacts.network.FactsApi
import com.chocomiruku.catsfacts.network.NetworkFact
import com.chocomiruku.catsfacts.network.asDomainModel
import com.chocomiruku.catsfacts.repository.FactsRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class FactsApiStatus { LOADING, ERROR, DONE }

class FactsOverviewViewModel(application: Application) : ViewModel() {

    private val factsRepository = FactsRepository()

    private val _status = MutableLiveData<FactsApiStatus>()

    val status: LiveData<FactsApiStatus>
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
            _status.value = FactsApiStatus.LOADING
            try {
                _facts.value = factsRepository.refreshFacts()
                _status.value = FactsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = FactsApiStatus.ERROR
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