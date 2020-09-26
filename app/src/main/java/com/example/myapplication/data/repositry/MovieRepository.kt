package com.example.myapplication.data.repositry

import android.content.Context
import com.example.myapplication.data.database.Movies.Movie
import com.example.myapplication.data.database.Movies.MoviesDatabase
import com.example.myapplication.data.database.Movies.TopMoviesDatabase
import com.example.myapplication.data.database.Reviews.Review
import com.example.myapplication.data.database.Reviews.ReviewDatabase
import com.example.myapplication.data.database.Videos.Video
import com.example.myapplication.data.database.Videos.VideoDatabase
import com.example.myapplication.data.network.APIclient
import com.example.myapplication.data.network.APIinterface
import com.example.myapplication.data.modules.MovieResponse
import com.example.myapplication.data.modules.VideoResponse
import com.example.myapplication.data.modules.ReviewResponse
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


object MovieRepository {

    private lateinit var moviesDatabase: MoviesDatabase
    private lateinit var topMoviesDatabase: TopMoviesDatabase
    private lateinit var videoDatabase: VideoDatabase
    private lateinit var reviewDatabase: ReviewDatabase

private val apiClient: APIinterface by lazy {
    APIclient.getClient().create(APIinterface::class.java)
}

    lateinit var movieData : List<Movie>
    lateinit var movieResponse: MovieResponse
    lateinit var topMovieData:List<Movie>
    lateinit var topMovieResponse: MovieResponse
    lateinit var vidData:Video
    lateinit var videoResponse: VideoResponse
    lateinit var movieReview: ReviewResponse
    lateinit var movieReviewDB: List<Review>
    val mapper = Mapper()
    private const val apiKey="2f1e25eb96a6de2a07fb4df24ebb1c19"
    private lateinit var msg:String


    fun requestMovies(callback: MovieCallBack, myPage: Int){

        apiClient.getPopularMovie(apiKey = apiKey,page = myPage)
            .enqueue(object: Callback<MovieResponse>{

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if(response.isSuccessful) {
                    movieResponse=response.body()!!
                    movieData = mapper.convertToMovie(movieResponse,"pop")
                    moviesDatabase.getMoviesDao().addMovies(movieData)
                    callback.onMoviesAvailable(movieData)
                } else if (response.code() == 404){
                    msg ="The movies aren't found"
                    callback.onMoviesUnavailable(msg)
                    callback.onMoviesAvailable(moviesDatabase.getMoviesDao().getMovies())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                msg ="Error while getting the movies"
                callback.onMoviesUnavailable(msg)
                callback.onMoviesAvailable(moviesDatabase.getMoviesDao().getMovies())
            }

        })

    }


    fun requestTopMovies(callback: TopMovieCallBack, myPage:Int){

        apiClient.getTopMovies(apiKey = apiKey, page = myPage)
            .enqueue(object: Callback<MovieResponse>{

                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    if(response.isSuccessful) {
                        topMovieResponse=response.body()!!
                       topMovieData  = mapper.convertToMovie(topMovieResponse,"top")
                        topMoviesDatabase.getTopMoviesDao().addTopMovies(topMovieData)
                        callback.onTopMoviesAvailable(topMovieData)
                    } else if (response.code() == 404){
                        msg ="The movies aren't found"
                        callback.onTopMoviesUnavailable(msg)
                        callback.onTopMoviesAvailable(topMoviesDatabase.getTopMoviesDao().getTopMovies())
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                    msg ="Error while getting the movies"
                    callback.onTopMoviesUnavailable(msg)
                    callback.onTopMoviesAvailable(topMoviesDatabase.getTopMoviesDao().getTopMovies())
                }

            })
    }



    fun requestVids(callback: VidCallBack,movieId:Long){
        if(this::vidData.isInitialized){
            callback.onVidsAvailable(vidData)
            return
        }
        apiClient.getMovieVideos(movieId,apiKey).enqueue(object: Callback<VideoResponse>{
            override fun onResponse(call: Call<VideoResponse>, response: Response<VideoResponse>) {
                if(response.isSuccessful) {
                    videoResponse=response.body()!!
                    vidData = mapper.convertToVideo(videoResponse)
                    videoDatabase.getVidsDao().addVids(vidData)
                    callback.onVidsAvailable(vidData)
                } else if (response.code() == 404){
                    msg ="The videos aren't found"
                    callback.onVidsUnavailable(msg)
                    callback.onVidsAvailable(videoDatabase.getVidsDao().getVids())
                }
            }

            override fun onFailure(call: Call<VideoResponse>, t: Throwable) {
                t.printStackTrace()
                msg ="Error while getting the videos"
                callback.onVidsUnavailable(msg)
                callback.onVidsAvailable(videoDatabase.getVidsDao().getVids())
            }

        })
    }


    fun requestMovieReviews(callback: ReviewCallBack,movieId: Long){

        if(this::movieReviewDB.isInitialized){
            callback.onReviewAvailable(movieReviewDB)
            return
        }


        apiClient.getMovieReviews(movieId,apiKey)
            .enqueue(object: Callback<ReviewResponse>{
                override fun onResponse(call: Call<ReviewResponse>, response: Response<ReviewResponse>)
                {
                    if(response.isSuccessful) {
                        movieReview=response.body()!!
                        movieReviewDB = mapper.convertToReview(movieReview)
                        reviewDatabase.getReviewsDao().addReviews(movieReviewDB)
                        callback.onReviewAvailable(movieReviewDB)
                    } else if (response.code() == 404){
                        msg ="The review aren't found"
                        callback.onReviewUnavailable(msg)
                        callback.onReviewAvailable(reviewDatabase.getReviewsDao().getReviews())
                    }
                }

                override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                    t.printStackTrace()
                    msg ="error while getting reviews"
                    callback.onReviewUnavailable(msg)
                    callback.onReviewAvailable(reviewDatabase.getReviewsDao().getReviews())
                }


            })

    }

    fun createDatabase(context: Context){
        moviesDatabase= MoviesDatabase.getDatabase(context)
        topMoviesDatabase= TopMoviesDatabase.getDatabase(context)
        videoDatabase= VideoDatabase.getDatabase(context)
        reviewDatabase= ReviewDatabase.getDatabase(context)
    }

    interface MovieCallBack{
        fun onMoviesAvailable(movies: List<Movie>)
        fun onMoviesUnavailable(msg:String)
    }

    interface TopMovieCallBack{
        fun onTopMoviesAvailable(movies: List<Movie>)
        fun onTopMoviesUnavailable(msg:String)
    }
    interface VidCallBack{
        fun onVidsAvailable(vids:Video?)
        fun onVidsUnavailable(msg:String)
    }

    interface ReviewCallBack{
        fun onReviewAvailable(reviews: List<Review>)
        fun onReviewUnavailable(msg:String)
    }

}
