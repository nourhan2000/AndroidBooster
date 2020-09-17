package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)
        getMyIntent()
    }
    fun getMyIntent(){
        if(intent.hasExtra("photo")&&intent.hasExtra("date")&&intent.hasExtra("overView")&&intent.hasExtra("vote"))
        {
            val photo :String = intent.getStringExtra("photo").toString()
            val date :String = intent.getStringExtra("date").toString()
            val overView:String = intent.getStringExtra("overView").toString()
            val vote :String = intent.getStringExtra("vote").toString()
            fillMyActivity(photo,date,overView,vote)

        }
    }
    fun fillMyActivity(photo :String,date :String,overView:String,vote :String){
        textView11.text=vote
        textView10.text=date
        textView9.text= overView
        Picasso.get().load(photo).into(imageView2)
    }

}