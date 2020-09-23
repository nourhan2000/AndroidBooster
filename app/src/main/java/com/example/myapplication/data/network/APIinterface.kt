package com.example.myapplication.data.network
import com.example.myapplication.data.modules.MovieResponse
import com.example.myapplication.data.modules.VideoResponse
import com.example.myapplication.data.modules.MoviesReviews
import com.example.myapplication.data.modules.ReviewResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIinterface {

    @GET("movie/popular")
    fun getPopularMovie(@Query ("api_key") apiKey:String, @Query("page") page: Int ): Call<MovieResponse >

    @GET("movie/{movie_id}/reviews")
    fun getMovieReviews(@Path("movie_id") movieId:Long, @Query ("api_key") apiKey:String, ): Call<ReviewResponse>


    @GET("movie/{movie_id}/videos")
    fun getMovieVideos( @Path("movie_id") movieId:Long,@Query ("api_key") apiKey:String): Call<VideoResponse>

    @GET("movie/top_rated")
    fun getTopMovies(@Query("api_key") apiKey:String, @Query("page") page: Int ):Call<MovieResponse>

}