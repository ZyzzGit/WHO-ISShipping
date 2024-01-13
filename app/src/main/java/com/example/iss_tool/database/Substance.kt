package com.example.iss_tool.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Substance(
    @PrimaryKey val id: Int,

    val substanceName: String?,
    val category: String?,
    val code:String?,
)