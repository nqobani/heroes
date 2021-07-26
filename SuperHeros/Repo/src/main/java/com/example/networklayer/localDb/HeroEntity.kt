package com.example.networklayer.localDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroesTable")
data class HeroEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "fullName")
    var fullName: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "occupation")
    var occupation: String,
)
