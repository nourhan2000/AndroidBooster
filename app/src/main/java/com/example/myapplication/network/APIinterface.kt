package com.example.myapplication.network
import com.example.myapplication.network.modules.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIinterface {

    @GET("movie/popular")
    fun getPopularMovie(@Query ("api_key") apiKey:String): Call<MovieResponse >
}