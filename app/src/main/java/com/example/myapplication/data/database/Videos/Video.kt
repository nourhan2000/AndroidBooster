package com.example.myapplication.data.database.Videos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Video (
    @PrimaryKey
    val vidId :Long,
    val vidKey : String,
    val name : String,
    val site : String,
    val type : String
)