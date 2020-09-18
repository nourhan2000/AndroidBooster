package com.example.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.data.database.Movies.Movie
import com.example.myapplication.recycler.MovieAdapter
import com.google.android.youtube.player.YouTubeBaseActivity


class MainActivity : AppCompatActivity() {

    val youtubeAPIkey = "AIzaSyAGF4s6LEPwk80wuf7v0gUG5ey8jNQS17I"
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.onError.observe(this, {
            handelMovieError(it)
        })

        mainViewModel.movieLiveData
            .observe(this, {
                bindMovieData(it)
                it.forEach {
                    mainViewModel.loadMovieReviews(it.movieId)
                    mainViewModel.loadMovieVideo(it.movieId)
                }
            })
        mainViewModel.topMovieLiveData
            .observe(this, {
                bindMovieData(it)
                it.forEach {
                    mainViewModel.loadMovieReviews(it.movieId)
                    mainViewModel.loadMovieVideo(it.movieId)
                }
            })

        mainViewModel.loadMovieData()

        val navController= Navigation
            .findNavController(this,R.id.main_container)

        NavigationUI.setupWithNavController(navigation_bottom,navController)

        mainViewModel.reviewLiveData.observe(this,{
            TODO("put in secound fragment")
        })
        mainViewModel.videoLiveData.observe(this,{
            TODO("put in secound fragment")
        })

    }

    private fun bindMovieData(movie: List<Movie>)
    {/*
        recycler_view.hasFixedSize()
        recycler_view.layoutManager = GridLayoutManager(this@MainActivity ,2)
        recycler_view.adapter = MovieAdapter( movie )*/
    }

    private fun handelMovieError(errorMsg: String)
    {
        Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_LONG).show()
    }



}