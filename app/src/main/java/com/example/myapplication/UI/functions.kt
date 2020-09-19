package com.example.myapplication.UI

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.database.Movies.Movie
import com.example.myapplication.recycler.MovieAdapter


fun bindMovieData(movie: List<Movie>,recyclerView: RecyclerView,context: Context)
{
    recyclerView.hasFixedSize()
    recyclerView.layoutManager = GridLayoutManager(context,2)
    recyclerView.adapter = MovieAdapter( movie )
}

 fun handelMovieError(errorMsg: String,context: Context)
{
    Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
}