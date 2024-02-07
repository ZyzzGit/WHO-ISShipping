package com.example.iss_tool.database

import androidx.lifecycle.LiveData

class SubstanceRepository(private val SubstanceDao: SubstanceDao) {
    val readAllData: LiveData<List<Substance>> = SubstanceDao.readAllData()

    suspend fun addSubstance(Substance: Substance) {
        SubstanceDao.addSubstance(Substance)
    }
}

