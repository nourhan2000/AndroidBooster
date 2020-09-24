package com.example.myapplication.UI

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.database.Movies.Movie
import com.example.myapplication.recycler.MovieAdapter


fun bindMovieData(context: Context,movie: List<Movie>,recyclerView: RecyclerView,type:String)
{
    recyclerView.hasFixedSize()
    recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
    MovieAdapter.type = type
    recyclerView.adapter = MovieAdapter(movie)
}

 fun handelMovieError(errorMsg: String,context: Context)
{
    Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
}
