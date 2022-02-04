package com.chocomiruku.catsfacts.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chocomiruku.catsfacts.domain.Fact

class FactDetailsViewModelFactory(private val fact: Fact, private val application: Application) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FactDetailsViewModel::class.java)) {
            return FactDetailsViewModel(fact, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}