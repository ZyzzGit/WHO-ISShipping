package com.example.iss_tool.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubstanceViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Substance>>
    private val repository: SubstanceRepository
//    private val readAnimalSub:List<String>
    init {
        val SubstanceDao = SubstanceDatabase.getDatabase(application).SubstanceDao()
        repository = SubstanceRepository(SubstanceDao)
        readAllData = repository.readAllData
//        readAnimalSub = repository.readAnimalSub

    }

    fun addSubstance(Substance: Substance) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSubstance(Substance)
        }
    }

    val _readAllData: LiveData<List<Substance>> get() = readAllData


}

