package com.example.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.data.database.Movie
import com.example.myapplication.recycler.MovieAdapter
import com.example.myapplication.data.repositry.MovieRepository
import com.example.myapplication.data.repositry.MovieRepository.requestMovies


class MainActivity : AppCompatActivity(), MovieRepository.MovieCallBack {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestMovies(this)
    }

    override fun onMoviesAvailable(movies: List<Movie>) {
        recycler_view.hasFixedSize()
        recycler_view.layoutManager = GridLayoutManager(this@MainActivity ,2)
        recycler_view.adapter = MovieAdapter(movies)

    }

    override fun onMoviesUnavailable(msg: String) {
        Toast.makeText(this@MainActivity ,msg,Toast.LENGTH_SHORT).show()
    }




}