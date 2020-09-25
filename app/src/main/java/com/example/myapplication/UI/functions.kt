package com.example.myapplication.UI

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.database.Movies.Movie
import com.example.myapplication.recycler.MovieAdapter


fun bindMovieData(context: Context,movie: List<Movie>,recyclerView: RecyclerView,type:String,isPagination:Boolean)
{
    val adapter =  MovieAdapter(movie)
    val layoutManager= GridLayoutManager(context,2)
    if(isPagination){
        adapter.updateAdapterData(movie)
        layoutManager.stackFromEnd
        return
    }
    recyclerView.hasFixedSize()
    recyclerView.layoutManager = layoutManager
    MovieAdapter.type = type
    recyclerView.adapter = adapter
}

 fun handelMovieError(errorMsg: String,context: Context)
{
    Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
}
