package com.example.iss_tool.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubstanceViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Substance>>
    val repository: SubstanceRepository
    init {
        val SubstanceDao = SubstanceDatabase.getDatabase(application).SubstanceDao()
        repository = SubstanceRepository(SubstanceDao)
        readAllData = repository.readAllData
    }

    fun addSubstance(Substance: Substance) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSubstance(Substance)
        }
    }
}

