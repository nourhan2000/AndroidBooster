package com.example.myapplication.UI

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.database.Movies.Movie
import com.example.myapplication.recycler.MovieAdapter


fun bindMovieData(recyclerView: RecyclerView,type:String,adapter: MovieAdapter,linearLayoutManager: LinearLayoutManager)
{
    recyclerView.hasFixedSize()
    recyclerView.layoutManager = linearLayoutManager
    MovieAdapter.type = type
    recyclerView.adapter = adapter
}

 fun handelMovieError(errorMsg: String,context: Context)
{
    Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
}

fun pagination(movies: List<Movie>,isPagination:Boolean,linearLayoutManager: LinearLayoutManager,adapter: MovieAdapter,recyclerView: RecyclerView){
    if (isPagination){
        linearLayoutManager.stackFromEnd
        adapter.updateAdapterData(movies)
    }else {
        setupRecycler(linearLayoutManager,adapter,recyclerView)
    }
}

fun setupRecycler(linearLayoutManager: LinearLayoutManager,adapter: MovieAdapter,recyclerView: RecyclerView) {

    recyclerView.layoutManager = linearLayoutManager
    recyclerView.adapter = adapter
}