package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.network.modules.MovieResponse
import com.example.myapplication.repositry.MovieRepository


class MainViewModel : ViewModel(), MovieRepository.MovieCallBack  {

    private val _movieLiveData: MutableLiveData<MovieResponse>
            by lazy { MutableLiveData() }
    val movieLiveData: LiveData<MovieResponse>
        get() = _movieLiveData

    private val _onError: MutableLiveData<String>
            by lazy { MutableLiveData() }
    val onError: LiveData<String>
        get() = _onError

    private lateinit var movieData: MovieResponse



    fun loadMovieData(cityName: String = "") {

        MovieRepository.requestMovies( this)
    }

    override fun onMoviesAvailable(movies: MovieResponse) {
        movieData = movies
        _movieLiveData.value = movieData
    }

    override fun onMoviesUnavailable(msg: String) {
        _onError.value = msg
    }
}