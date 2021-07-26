package com.example.networklayer.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Appearance(
        @SerializedName("eye-color")
        @Expose
    val eyeColor: String,
        @SerializedName("gender")
        @Expose
    val gender: String,
        @SerializedName("hair-color")
        @Expose
    val hairColor: String,
        @SerializedName("height")
    val height: List<String>,
        @SerializedName("race")
    val race: String,
        @SerializedName("weight")
    val weight: List<String>
)