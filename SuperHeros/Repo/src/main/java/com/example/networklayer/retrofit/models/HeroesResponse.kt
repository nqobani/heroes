package com.example.networklayer.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HeroesResponse(
        @SerializedName("response")
        @Expose
    val response: String,
    @SerializedName("results")
    @Expose
    val serviceResults: List<Result>,
        @SerializedName("results-for")
        @Expose
    val resultsFor: String
)