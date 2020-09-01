package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.recycler.HorizentalAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)
        Picasso.get().load(HorizentalAdapter.horizentalModel.PosterPath).into(imageView2)
        textView9.text= HorizentalAdapter.horizentalModel.overview
        textView10.text=HorizentalAdapter.horizentalModel.releaseDate
        textView11.text= HorizentalAdapter.horizentalModel.voteAverage.toString()
    }
}