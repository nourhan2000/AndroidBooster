package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.data.database.Movies.Movie
import com.example.myapplication.recycler.MovieAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

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
            })

        mainViewModel.loadMovieData()


        val navController = Navigation
            .findNavController(this,R.id.main_container)

        NavigationUI.setupWithNavController(navigation_bottom,navController)



    }

    private fun bindMovieData(movie: List<Movie>)
    {
        recycler_view.hasFixedSize()
        recycler_view.layoutManager = GridLayoutManager(this@MainActivity ,2)
        recycler_view.adapter = MovieAdapter( movie )
    }

    private fun handelMovieError(errorMsg: String)
    {
        Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_LONG).show()
    }

}