package com.example.myapplication.repositry

import com.example.myapplication.network.APIclient
import com.example.myapplication.network.APIinterface
import com.example.myapplication.network.modules.MovieResponse
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import retrofit2.create


object MovieRepositry {
private val apiClient: APIinterface by lazy {
    APIclient.getClient().create(APIinterface::class.java)
}
    private const val apiKey="2a6920b91206c06c1978f5e348c1c98e"
    private lateinit var movieData : List<MovieResponse>
    fun requestMovies(callback: MovieCallBack){
        if(this::movieData.isInitialized){
            callback.onMoviesAvailble(movieData)
            return
        }
        apiClient.getPoularMovie(apiKey).enqueue(object: Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
    interface MovieCallBack{
        fun onMoviesAvailble(movies: List<MovieResponse>)
        fun onMoviesUnavailble(msg:String)
    }
}
