package com.example.networklayer.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Connections(
        @SerializedName("group-affiliation")
        @Expose
    val groupAffiliation: String,
        @SerializedName("relatives")
        @Expose
    val relatives: String
)