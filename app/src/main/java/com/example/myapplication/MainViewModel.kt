package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.database.Movies.Movie
import com.example.myapplication.data.database.Reviews.Review
import com.example.myapplication.data.database.Videos.Video
import com.example.myapplication.data.repositry.MovieRepository


class MainViewModel (application: Application)
    : AndroidViewModel(application), MovieRepository.MovieCallBack, MovieRepository.ReviewCallBack, MovieRepository.VidCallBack  {

    private val _movieLiveData: MutableLiveData<List<Movie>>
            by lazy { MutableLiveData() }
    val movieLiveData: LiveData<List<Movie>>
        get() = _movieLiveData

    private val _videoLiveData: MutableLiveData<List<Video>>
            by lazy { MutableLiveData() }
    val videoLiveData: LiveData<List<Video>>
        get() =_videoLiveData

    private val _reviewLiveData: MutableLiveData<List<Review>>
            by lazy { MutableLiveData() }
    val reviewLiveData: LiveData<List<Review>>
        get() =_reviewLiveData

    private val _onError: MutableLiveData<String>
            by lazy { MutableLiveData() }
    val onError: LiveData<String>
        get() = _onError

    private lateinit var movieData: List<Movie>
    private lateinit var vidData:List<Video>
    private lateinit var movieReviewDB: List<Review>

    init{
        MovieRepository.createDatabase(application)
    }



    fun loadMovieData() {
        if(this::movieData.isInitialized) {
            _movieLiveData.value = movieData
            return}
            MovieRepository.requestMovies(this)
    }

    fun loadMovieVideo(movieId:Long){
        if(this::vidData.isInitialized){
            _videoLiveData.value = vidData
            return}
        MovieRepository.requestVids(this,movieId)
    }

    fun loadMovieReviews(movieId:Long){
        if(this::movieReviewDB.isInitialized){
            _reviewLiveData.value = movieReviewDB
            return}
        MovieRepository.requestMovieReviews(this,movieId)
    }

    override fun onMoviesUnavailable(msg: String) {
        _onError.value = msg
    }

    override fun onMoviesAvailable(movies: List<Movie>) {
        movieData = movies
        _movieLiveData.value = movieData
    }

    override fun onVidsAvailable(vids: List<Video>) {
        vidData=vids
        _videoLiveData.value=vidData
    }

    override fun onVidsUnavailable(msg: String) {
        _onError.value = msg
    }

    override fun onReviewAvailable(reviews: List<Review>) {
        movieReviewDB=reviews
        _reviewLiveData.value=movieReviewDB
    }

    override fun onReviewUnavailable(msg: String) {
        _onError.value = msg
    }
}