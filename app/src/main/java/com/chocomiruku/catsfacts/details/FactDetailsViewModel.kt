package com.chocomiruku.catsfacts.details

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


class FactDetailsViewModel(fact: Fact, application: Application) : ViewModel() {

    private val database = getDatabase(application)
    private val factsRepository = FactsRepository(database)

    private val _selectedFact = MutableLiveData<Fact>()

    val selectedFact: LiveData<Fact>
        get() = _selectedFact

    private val _status = MutableLiveData<ApiStatus>()

    val status: LiveData<ApiStatus>
        get() = _status

    private val _photoUrl = MutableLiveData<String?>()

    val photoUrl: LiveData<String?>
        get() = _photoUrl

    private val _isAddedToFavourites = MutableLiveData<Boolean>()

    val isAddedToFavourites: LiveData<Boolean>
        get() = _isAddedToFavourites

    init {
        _selectedFact.value = fact
        getPhoto()
        checkFavourites(fact)
    }

    private fun getPhoto() {
        viewModelScope.launch {
            try {
                _photoUrl.value = factsRepository.getCatPhotoUrl()
            } catch (e: Exception) {
                _photoUrl.value = null
                _status.value = ApiStatus.ERROR
            }
        }
    }

    private fun checkFavourites(fact: Fact) {
        viewModelScope.launch {
            _isAddedToFavourites.value = factsRepository.checkIfIsAddedToFavourites(fact)
        }
    }

    fun updateFavourites() {
        val fact = selectedFact.value
        fact?.let {
            viewModelScope.launch {
                if (!_isAddedToFavourites.value!!) {
                    factsRepository.addToFavourites(fact)
                    _isAddedToFavourites.value = true
                } else {
                    factsRepository.deleteFromFavourites(fact)
                    _isAddedToFavourites.value = false
                }
            }
        }
    }
}