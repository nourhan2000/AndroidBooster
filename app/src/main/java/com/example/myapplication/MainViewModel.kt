package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.database.Movie
import com.example.myapplication.data.repositry.MovieRepository


class MainViewModel (application: Application) : AndroidViewModel(application), MovieRepository.MovieCallBack  {

    private val _movieLiveData: MutableLiveData<List<Movie>>
            by lazy { MutableLiveData() }
    val movieLiveData: LiveData<List<Movie>>
        get() = _movieLiveData

    private val _onError: MutableLiveData<String>
            by lazy { MutableLiveData() }
    val onError: LiveData<String>
        get() = _onError

    private lateinit var movieData: List<Movie>
    init{
        MovieRepository.createDatabase(application)
    }



    fun loadMovieData() {
        if(this::movieData.isInitialized) {
            _movieLiveData.value = movieData
            return}
            MovieRepository.requestMovies(this)

    }


    override fun onMoviesAvailable(movies: List<Movie>) {
        movieData = movies
        _movieLiveData.value = movieData
    }

    override fun onMoviesUnavailable(msg: String) {
        _onError.value = msg
    }
}