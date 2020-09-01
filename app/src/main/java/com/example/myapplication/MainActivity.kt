package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    verticalRecyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verticalRecyclerView = (RecyclerView)findViewById(R.id.recycler_view)
        verticalRecyclerView .setHasFixedSize(true)
        verticalRecyclerView .setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false))



    }
}