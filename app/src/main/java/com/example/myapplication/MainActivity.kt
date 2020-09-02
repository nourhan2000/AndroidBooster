package com.example.myapplication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import com.example.myapplication.network.modules.MovieResponse
import com.example.myapplication.network.modules.MoviesDetails
import com.example.myapplication.recycler.VerticalAdapter
import com.example.myapplication.repositry.MovieRepositry
import com.example.myapplication.repositry.MovieRepositry.requestMovies
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.horizental_item.*


class MainActivity : AppCompatActivity(), MovieRepositry.MovieCallBack {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestMovies(this)
        //produces an error as the image is not found when the program starts
        /*image.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
           startActivity(intent)
        }*/
    }

    override fun onMoviesAvailble(movies: MovieResponse) {
        recycler_view.hasFixedSize()
        recycler_view.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,false)
        recycler_view.adapter = VerticalAdapter( movies.MoviesList )

    }

    override fun onMoviesUnavailble(msg: String) {
        Toast.makeText(this@MainActivity ,msg,Toast.LENGTH_SHORT).show()
    }
}