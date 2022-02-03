package com.chocomiruku.catsfacts.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.chocomiruku.catsfacts.domain.Fact
import kotlinx.coroutines.Deferred

@Dao
interface FactDao {
    @Query("select * from databasefact")
    fun getFavourites(): LiveData<List<DatabaseFact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fact: DatabaseFact)

    @Delete
    fun delete(fact: DatabaseFact)

    @Query("SELECT * from databasefact WHERE id = :id LIMIT 1")
    fun getFactFromFavourites(id: String) : DatabaseFact?
}

@Database(entities = [DatabaseFact::class], version = 4)
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