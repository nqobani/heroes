package com.example.networklayer.interactors;

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.networklayer.localDb.HeroEntity
import com.example.networklayer.localDb.HeroesDatabase
import com.example.networklayer.retrofit.HeroesAPIRetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.net.UnknownHostException

class HeroesInteractor
constructor(
    private val context: Context,
    private val heroesAPIRetrofitService: HeroesAPIRetrofitService
){

    companion object{
        const val TAG = "HeroesInteractor:"
    }
    private val database = Room.databaseBuilder(context, HeroesDatabase::class.java, HeroesDatabase.DATABASE_NAME).build()
    suspend fun searchHeroes(name: String): Flow<DataState<List<HeroEntity>>> = flow {
        emit(DataState.Loading())
        Log.d(TAG, "LOADING DATA STATE")
        try {
            val heroResponse = heroesAPIRetrofitService.searchHeroesByName(name)
            if (heroResponse.body()?.serviceResults != null) {
                for (hero in heroResponse.body()?.serviceResults!!) {
                    database.heroesDao().insert(HeroEntity(
                        id = hero.id,
                        name = hero.name,
                        image = hero.image.url,
                        fullName = hero.biography.fullName,
                        occupation = hero.work.occupation
                    ))
                }
                Log.d(TAG, "SUCCESS: $heroResponse")
            }
            val heroes = database.heroesDao().searchByName(name)
            if(heroes.isNotEmpty()) {
                emit(DataState.Success(heroes))
            } else {
                emit(DataState.NoResult)
            }
        } catch (e: UnknownHostException){
            val heroes = database.heroesDao().searchByName(name)
            emit(DataState.Success(heroes))
            Log.d(TAG, "SUCCESS: $e")
        }
        catch (e: Exception){
            emit(DataState.Failure(e.message!!))
            Log.d(TAG, "SUCCESS: $e")
        }
    }
}