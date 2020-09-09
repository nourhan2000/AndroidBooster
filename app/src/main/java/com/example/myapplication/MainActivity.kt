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
import androidx.activity.viewModels


class MainActivity : AppCompatActivity(){

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

        mainViewModel.loadMovieData()




    }

    private fun bindMovieData(movie: MovieResponse)
    {

        recycler_view.hasFixedSize()
        recycler_view.layoutManager = GridLayoutManager(this@MainActivity ,2)
        recycler_view.adapter = MovieAdapter( movie.MoviesList )
    }

    private fun handelMovieError(errorMsg: String)
    {
        Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_LONG).show()
    }






}