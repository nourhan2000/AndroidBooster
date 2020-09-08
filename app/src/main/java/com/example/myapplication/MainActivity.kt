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
import androidx.activity.viewModels


class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.movieLiveData
            .observe(this, {

                bindMovieData(it)
            })

        mainViewModel.onError.observe(this, {
            handelMovieError(it)
        })


    }




    private fun bindMovieData(movies: List<Movie>) {
        recycler_view.hasFixedSize()
        recycler_view.layoutManager = GridLayoutManager(this@MainActivity ,2)
        recycler_view.adapter = MovieAdapter(movies)
    }

    private fun handelMovieError(msg: String) {
        Toast.makeText(this@MainActivity ,msg,Toast.LENGTH_SHORT).show()
    }



}