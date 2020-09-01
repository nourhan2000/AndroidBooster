package com.example.myapplication.network.modules

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val TotalResults: Int,
    @SerializedName("total_pages")
    val TotalPages: Int,
    @SerializedName("results")
    val MoviesList : List<MoviesDetails>
)

data class MoviesDetails(
    @SerializedName("poster_path")
    val PosterPath: Int,
    @SerializedName("original_title")
    val OriginalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
)
