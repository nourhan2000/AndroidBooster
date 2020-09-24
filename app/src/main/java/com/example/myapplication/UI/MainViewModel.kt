package com.example.myapplication.UI

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.database.Movies.Movie
import com.example.myapplication.data.database.Reviews.Review
import com.example.myapplication.data.database.Videos.Video
import com.example.myapplication.data.repositry.MovieRepository


class MainViewModel (application: Application)
    : AndroidViewModel(application), MovieRepository.MovieCallBack, MovieRepository.TopMovieCallBack,
    MovieRepository.ReviewCallBack, MovieRepository.VidCallBack,MovieRepository.FavoriteCallBack  {

    private val _movieLiveData: MutableLiveData<List<Movie>>
            by lazy { MutableLiveData() }
    val movieLiveData: LiveData<List<Movie>>
        get() = _movieLiveData

    private val _topMovieLiveData: MutableLiveData<List<Movie>>
            by lazy { MutableLiveData() }
    val topMovieLiveData: LiveData<List<Movie>>
        get() = _topMovieLiveData

    private val _favMovieLiveData: MutableLiveData<List<Movie>>
            by lazy { MutableLiveData() }
    val favMovieLiveData: LiveData<List<Movie>>
        get() = _favMovieLiveData

    private val _videoLiveData: MutableLiveData<Video>
            by lazy { MutableLiveData() }
    val videoLiveData: LiveData<Video>
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
    private lateinit var topMovieData: List<Movie>
    private lateinit var vidData:Video
    private lateinit var movieReviewDB: List<Review>
    private lateinit var favMovies:List<Movie>

    init{
        MovieRepository.createDatabase(application)
    }

    fun loadFavMovie(){
       if (this::favMovies.isInitialized){
           _favMovieLiveData.value=favMovies
           return
       }
        MovieRepository.requestFavMovies(this)
    }

    fun loadMovieData(myPage: Int) {
            MovieRepository.requestMovies(this, myPage)
    }
    fun loadTopMovieData(myPage: Int) {
        MovieRepository.requestTopMovies(this, myPage)
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

    override fun onTopMoviesUnavailable(msg: String) {
        _onError.value = msg
    }

    override fun onTopMoviesAvailable(movies: List<Movie>) {
        topMovieData = movies
        _topMovieLiveData.value = topMovieData
    }

    override fun onVidsAvailable(vids: Video?) {
        if(vids!=null){
            vidData=vids
            _videoLiveData.value=vidData
        }
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

    override fun onFavMoviesAvailable(movies: List<Movie>) {
        favMovies = movies
        _favMovieLiveData.value=favMovies
    }

    override fun onFavMoviesUnavailable(msg: String) {
        _onError.value = msg
    }
}

