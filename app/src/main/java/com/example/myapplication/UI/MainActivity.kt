package com.example.myapplication.UI
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Video.Thumbnails.VIDEO_ID
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.data.database.Movies.Movie
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer


class MainActivity : AppCompatActivity() {


    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController= Navigation
            .findNavController(this, R.id.main_container)

        NavigationUI.setupWithNavController(navigation_bottom,navController)

        val appBarConfiguration= AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController,appBarConfiguration)
    }
}
//        mainViewModel.onError.observe(this, {
//
//        })
//
//        mainViewModel.movieLiveData
//            .observe(this, {
//
//                it.forEach {
//                    mainViewModel.loadMovieReviews(it.movieId)
//                    mainViewModel.loadMovieVideo(it.movieId)
//                }
//            })
//        mainViewModel.topMovieLiveData
//            .observe(this, {
//
//                it.forEach {
//                    mainViewModel.loadMovieReviews(it.movieId)
//                    mainViewModel.loadMovieVideo(it.movieId)
//                }
//            })
//
//        mainViewModel.loadMovieData()
//
//        mainViewModel.reviewLiveData.observe(this,{
//
//        })
//        mainViewModel.videoLiveData.observe(this,{
//
//        })

