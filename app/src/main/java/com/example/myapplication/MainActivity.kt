package com.example.myapplication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.network.modules.MovieResponse
import com.example.myapplication.recycler.HorizentalAdapter
import com.example.myapplication.repositry.MovieRepositry
import com.example.myapplication.repositry.MovieRepositry.requestMovies
import kotlinx.android.synthetic.main.horizental_item.*


class MainActivity : AppCompatActivity(), MovieRepositry.MovieCallBack,
    HorizentalAdapter.OnImageListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestMovies(this)
        /*line.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
           startActivity(intent)
        }*/
    }

    override fun onMoviesAvailble(movies: MovieResponse) {
        recycler_view.hasFixedSize()
        recycler_view.layoutManager = GridLayoutManager(this@MainActivity ,2)
        recycler_view.adapter = HorizentalAdapter( movies.MoviesList )

    }

    override fun onMoviesUnavailble(msg: String) {
        Toast.makeText(this@MainActivity ,msg,Toast.LENGTH_SHORT).show()
    }

    override fun onImageClick(position: Int) {
        TODO("Not yet implemented")
    }
}