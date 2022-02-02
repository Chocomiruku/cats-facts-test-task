package com.chocomiruku.catsfacts.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FactDao {
    @Query("select * from databasefact")
    fun getFavourites(): LiveData<List<DatabaseFact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fact: DatabaseFact)

    @Delete
    fun delete(fact: DatabaseFact)
}

@Database(entities = [DatabaseFact::class], version = 2)
abstract class FactsDatabase : RoomDatabase() {
    abstract val factDao: FactDao
}

private lateinit var INSTANCE: FactsDatabase

fun getDatabase(context: Context): FactsDatabase {
    synchronized(FactsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                FactsDatabase::class.java,
                "facts").build()
        }
    }
    return INSTANCE
}