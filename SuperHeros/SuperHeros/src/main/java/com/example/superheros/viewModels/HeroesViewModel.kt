package com.example.superheros.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networklayer.interactors.DataState
import com.example.networklayer.interactors.HeroesInteractor
import com.example.networklayer.localDb.HeroEntity
import com.example.networklayer.retrofit.HeroesAPIRetrofitService.Companion.getHeroesAPIRetrofitServiceInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HeroesViewModel:  ViewModel() {
    lateinit var heroesInteractor: HeroesInteractor
    private val _heroes: MutableLiveData<DataState<List<HeroEntity>>> = MutableLiveData()
    val heroes: LiveData<DataState<List<HeroEntity>>> get() = _heroes

    fun setStateEvent(mainIntents: MainIntents, context: Context) {
        heroesInteractor = HeroesInteractor(context, getHeroesAPIRetrofitServiceInstance(context))
        viewModelScope.launch {
            when (mainIntents) {
                is MainIntents.searchHeroe -> {
                    heroesInteractor.searchHeroes(mainIntents.name).onEach {
                        _heroes.value = it
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    sealed class MainIntents{
        class searchHeroe(val name: String) : MainIntents()
        object none: MainIntents()
    }
}