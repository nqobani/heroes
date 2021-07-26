package com.example.networklayer.localDb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HeroEntity::class], version = 1)
abstract class HeroesDatabase: RoomDatabase() {
    abstract fun heroesDao() : HeroesDao

    companion object{
        const val DATABASE_NAME = "heroes_db"
    }
}