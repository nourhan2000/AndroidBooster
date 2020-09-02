package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.recycler.MovieAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)

        val photo = "https://image.tmdb.org/t/p/w500/${MovieAdapter.movieModel.PosterPath}"
        Picasso.get().load(photo).into(imageView2)

        textView9.text= MovieAdapter.movieModel.overview
        textView10.text=MovieAdapter.movieModel.releaseDate
        textView11.text= MovieAdapter.movieModel.voteAverage.toString()
    }
}