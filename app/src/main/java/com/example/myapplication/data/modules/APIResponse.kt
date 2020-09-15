package com.example.myapplication.data.modules

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
    @SerializedName("id")
       val movieId : Long,
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
       val releaseDate: String
)
data class VideoResponse(
    @SerializedName("id")
    val vidsId :Long,
    @SerializedName("results")
    val vidsList : ArrayList<VideoResults> = ArrayList<VideoResults>()
)
data class VideoResults(
    //@SerializedName("")
    @SerializedName("id")
    val vidId :Long,
    @SerializedName("key")
    val vidKey : String,
    @SerializedName("name")
    val name : String,
    @SerializedName("site")
    val site : String,
    @SerializedName("type")
    val type : String
)

data class ReviewResponse(
    @SerializedName("id")
    val ReviewId :Long,
   @SerializedName("results")
   val reviewResult: ArrayList<MoviesReviews>
)

data class MoviesReviews(
    @SerializedName("id")
    val ReviewId :Long,
    @SerializedName("content")
    val movieContent: String
)
