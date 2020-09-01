package com.example.myapplication.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.network.modules.MoviesDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.vertical_item.view.*

class VerticalAdapter (private val context: Context, private val postList :List<VerticalModel>): RecyclerView.Adapter<VerticalAdapter.ItemViewHolder>() {


        class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
              val myRecycler:RecyclerView = itemView.recycler_view_1
            val text : TextView = itemView.text1
              val button = itemView.btn
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.vertical_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
       val verticalModel: VerticalModel = postList[position]
        val title: String = vertivalModel.getTitle()
        val singleItem : ArrayList<HorizntalModel> = verticalModel.getArrayList()
        holder.text.setText(title)
        holder.myRecycler.setHasFixedSize(true)
        holder.myRecycler.setLayoutManager(LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false))
        holder.myRecycler.setAdapter(HorizentalAdapter(context,singleItem))
        holder.button.setOnClickListener(
            {
                @Override
                fun onClick(v: View) {
                    Toast.makeText(context, VerticalModel.getName(), Toast.LENGTH_SHORT).show()
                }


            }  )
    }
    override fun getItemCount(): Int {
        return VerticalModel.size
    }

  }
    fun postarImageData(moviesDetails: MoviesDetails)
    {
        Picasso.get().load("https://api.themoviedb.org/3/${MoviesDetails.poster_path}").into()
    }

}