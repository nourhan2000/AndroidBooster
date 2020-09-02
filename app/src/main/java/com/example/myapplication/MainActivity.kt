package com.example.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.network.modules.MovieResponse
import com.example.myapplication.recycler.MovieAdapter
import com.example.myapplication.repositry.MovieRepository
import com.example.myapplication.repositry.MovieRepository.requestMovies


class MainActivity : AppCompatActivity(), MovieRepository.MovieCallBack {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestMovies(this)
    }

    override fun onMoviesAvailable(movies: MovieResponse) {
        recycler_view.hasFixedSize()
        recycler_view.layoutManager = GridLayoutManager(this@MainActivity ,2)
        recycler_view.adapter = MovieAdapter( movies.MoviesList )
    }

    override fun onMoviesUnavailable(msg: String) {
        Toast.makeText(this@MainActivity ,msg,Toast.LENGTH_SHORT).show()
    }




}