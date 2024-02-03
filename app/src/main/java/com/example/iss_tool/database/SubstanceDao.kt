package com.example.iss_tool.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface SubstanceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSubstance(substance:Substance)
    @Insert
    suspend fun insertEntities(entities: List<Substance>)
    @Query("SELECT * FROM Substance ORDER BY id ASC")
    fun readAllData(): LiveData<List<Substance>>

//    @Query("SELECT substanceName FROM Substance WHERE code='UN 2814'")
//    fun readAnimalSub():List<String>
}