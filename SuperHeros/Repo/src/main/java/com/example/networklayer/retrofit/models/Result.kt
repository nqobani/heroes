package com.example.networklayer.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
        @SerializedName("appearance")
        @Expose
    val appearance: Appearance,
        @SerializedName("biography")
        @Expose
    val biography: Biography,
        @SerializedName("connections")
        @Expose
    val connections: Connections,
        @SerializedName("id")
        @Expose
    val id: Int,
        @SerializedName("image")
        @Expose
    val image: Image,
        @SerializedName("name")
        @Expose
    val name: String,
        @SerializedName("powerstats")
        @Expose
    val powerstats: Powerstats,
        @SerializedName("work")
        @Expose
    val work: Work
)