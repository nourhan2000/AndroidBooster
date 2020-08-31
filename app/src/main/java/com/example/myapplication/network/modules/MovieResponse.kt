package com.example.myapplication.network.modules

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val MoviesList : List<MoviesDetails>
)

data class WeatherDetails(TODO
//TODO 7oto hena el7agat eli gowa elresults(original_title,poster_path)
)