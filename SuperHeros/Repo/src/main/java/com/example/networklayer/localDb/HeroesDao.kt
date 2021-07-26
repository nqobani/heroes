package com.example.networklayer.localDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeroesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(heroEntity: HeroEntity) : Long

    @Query("SELECT * FROM HEROESTABLE")
    suspend fun get(): List<HeroEntity>

    @Query("SELECT * FROM HEROESTABLE WHERE name like '%'||:query||'%'")
    suspend fun searchByName(query: String?): List<HeroEntity>
}