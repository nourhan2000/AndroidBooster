package com.example.myapplication.data.database.Movies

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity

data class Movie (
    @PrimaryKey
    val movieId : Long,
    val PosterPath: String,
    val OriginalTitle: String,
    val originalLanguage: String,
    val voteAverage: Float,
    val overview: String,
    val releaseDate: String,
    val type : String,
    var isFavorite: Boolean=false
)