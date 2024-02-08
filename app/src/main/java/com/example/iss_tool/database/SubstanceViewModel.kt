package com.example.iss_tool.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubstanceViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllData: LiveData<List<Substance>>
    private val readAnimalSub: LiveData<List<Substance>>
    private val readHumanSub: LiveData<List<Substance>>

    private val repository: SubstanceRepository

    init {
        val SubstanceDao = SubstanceDatabase.getDatabase(application).SubstanceDao()
        repository = SubstanceRepository(SubstanceDao)
        readAllData = repository.readAllData
        readAnimalSub = repository.readAnimalSub
        readHumanSub = repository.readHumanSub


    }

    fun addSubstance(Substance: Substance) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSubstance(Substance)
        }
    }

    val _readAllData: LiveData<List<Substance>> get() = readAllData
    val _readAnimalSub: LiveData<List<Substance>> get() = readAnimalSub
    val _readHumanSub: LiveData<List<Substance>> get() = readHumanSub



}

