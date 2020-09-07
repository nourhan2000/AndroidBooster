package com.example.myapplication.repositry

import com.example.myapplication.data.Movie
import android.content.Context
import com.example.myapplication.network.APIclient
import com.example.myapplication.network.APIinterface
import com.example.myapplication.network.modules.MovieResponse
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


object MovieRepository {

    private lateinit var  moviesDatabase: MoviesDatabase

private val apiClient: APIinterface by lazy {
    APIclient.getClient().create(APIinterface::class.java)
}

    private const val apiKey="2f1e25eb96a6de2a07fb4df24ebb1c19"
    private lateinit var msg:String
    lateinit var movieData : List<Movie>

    fun requestMovies(callback: MovieCallBack){

        if(this::movieData.isInitialized){
            callback.onMoviesAvailable(movieData)
            return
        }

        apiClient.getPopularMovie(apiKey).enqueue(object: Callback<MovieResponse>{

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if(response.isSuccessful) {
                    movieData = convertToMovie(response.body()!!)
                    callback.onMoviesAvailable(movieData)
                } else if (response.code() == 404){
                    msg ="The movies aren't found"
                    callback.onMoviesUnavailable(msg)
                    TODO("call movies availble")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                msg ="Error while getting the movies"
                callback.onMoviesUnavailable(msg)
                TODO("call movies availble")
            }

        })

    }
    fun convertToMovie(movieResponse: MovieResponse): List<Movie>{
        val movies = mutableListOf<Movie>()
        movieResponse.MoviesList.forEach{
            movies.add(Movie(it.movieId,it.PosterPath,it.OriginalTitle,it.originalLanguage,it.voteAverage,it.overview,it.releaseDate))
        }
        return movies
    }

    interface MovieCallBack{
        fun onMoviesAvailable(movies: List<Movie>)
        fun onMoviesUnavailable(msg:String)
    }

    fun createDatabase(context: Context){
        moviesDatabase= MoviesDatabase.getDatabase(context)
    }
}
