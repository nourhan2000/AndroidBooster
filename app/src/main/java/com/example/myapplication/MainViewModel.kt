package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.database.Movie
import com.example.myapplication.data.modules.MovieResponse
import com.example.myapplication.data.repositry.MovieRepository

class MainViewModel: ViewModel(), MovieRepository.MovieCallBack {

    private val _movieLiveData: MutableLiveData<MovieResponse>
            by lazy { MutableLiveData() }
    val movieLiveData: LiveData<MovieResponse>
        get() = _movieLiveData

    private val _onError: MutableLiveData<String>
            by lazy { MutableLiveData() }
    val onError: LiveData<String>
        get() = _onError

    private lateinit var movieData: MovieResponse



    fun loadMovieData() {


        MovieRepository.requestMovies( this)
    }



    override fun onMoviesAvailable(movies: List<Movie>) {
        movieData = movies
        _movieLiveData.value = movieData
    }

    override fun onMoviesUnavailable(msg: String) {
        _onError.value = msg
    }
}