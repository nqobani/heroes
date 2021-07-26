package com.example.networklayer.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Biography(
        @SerializedName("aliases")
        @Expose
    val aliases: List<String>,
        @SerializedName("alignment")
        @Expose
    val alignment: String,
        @SerializedName("alter-egos")
        @Expose
    val alterEgos: String,
        @SerializedName("first-appearance")
        @Expose
    val firstAppearance: String,
        @SerializedName("full-name")
        @Expose
    val fullName: String,
        @SerializedName("place-of-birth")
        @Expose
    val placeOfBirth: String,
        @SerializedName("publisher")
        @Expose
    val publisher: String
)