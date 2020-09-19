package com.example.myapplication.UI
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.data.database.Movies.Movie


class MainActivity : AppCompatActivity() {

    val youtubeAPIkey = "AIzaSyAGF4s6LEPwk80wuf7v0gUG5ey8jNQS17I"
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.onError.observe(this, {

        })

        mainViewModel.movieLiveData
            .observe(this, {

                it.forEach {
                    mainViewModel.loadMovieReviews(it.movieId)
                    mainViewModel.loadMovieVideo(it.movieId)
                }
            })
        mainViewModel.topMovieLiveData
            .observe(this, {

                it.forEach {
                    mainViewModel.loadMovieReviews(it.movieId)
                    mainViewModel.loadMovieVideo(it.movieId)
                }
            })

        mainViewModel.loadMovieData()

        mainViewModel.reviewLiveData.observe(this,{

        })
        mainViewModel.videoLiveData.observe(this,{

        })


        val navController= Navigation
            .findNavController(this, R.id.main_container)

        NavigationUI.setupWithNavController(navigation_bottom,navController)

        val appBarConfiguration= AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController,appBarConfiguration)



    }

}