package com.example.networklayer.retrofit

import android.content.Context
import com.example.networklayer.R
import com.example.networklayer.retrofit.models.HeroesResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroesAPIRetrofitService {

    @GET("search/{name}")
    suspend fun searchHeroesByName(@Path("name") name:String): Response<HeroesResponse>

    companion object{
        var retrofitService: HeroesAPIRetrofitService? = null
        fun getHeroesAPIRetrofitServiceInstance(context: Context) : HeroesAPIRetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(context.getString(R.string.base_url))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(HeroesAPIRetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}
