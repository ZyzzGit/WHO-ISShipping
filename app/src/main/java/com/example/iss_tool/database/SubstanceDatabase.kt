package com.example.iss_tool.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Substance::class], version = 1, exportSchema = false)
abstract class SubstanceDatabase : RoomDatabase() {
    abstract fun SubstanceDao(): SubstanceDao

    companion object {
        @Volatile
        private var INSTANCE: SubstanceDatabase? = null

        fun getDatabase(context: Context): SubstanceDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SubstanceDatabase::class.java,
                    "SubstanceDB"
                ).build()
                INSTANCE = instance
                return instance

            }
        }

    }
}