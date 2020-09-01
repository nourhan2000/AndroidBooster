package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val verticalRecyclerView : RecyclerView = recycler_view
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verticalRecyclerView .setHasFixedSize(true)
        verticalRecyclerView .setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false))



    }
}