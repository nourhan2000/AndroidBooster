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
    val MoviesList: ArrayList<MoviesDetails> = ArrayList<MoviesDetails>()
)

data class MoviesDetails(
    @SerializedName("poster_path")
    val PosterPath: String,
    @SerializedName("original_title")
    val OriginalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
)
