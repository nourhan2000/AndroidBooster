package com.example.myapplication.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class HorizentalAdapter (private val context: Context, private val postList :List<HorizntalModel>): RecyclerView.Adapter<HorizentalAdapter.HorizentalItemViewHolder>() {

    class HorizentalItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizentalItemViewHolder {
        return VerticalAdapter.ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.Horizental_item, parent, false))
    }

    override fun onBindViewHolder(holder: HorizentalItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}