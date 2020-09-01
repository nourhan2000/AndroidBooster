package com.example.myapplication.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.network.modules.MoviesDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.horizental_item.view.*

class HorizentalAdapter (private val context: Context, private val postList :ArrayList<MoviesDetails>): RecyclerView.Adapter<HorizentalAdapter.HorizentalItemViewHolder>() {

    class HorizentalItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val textViewTitle:TextView =itemView.text_title_horizental
        val imageView:ImageView=itemView.image

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizentalItemViewHolder {
        return HorizentalItemViewHolder(LayoutInflater.from(context).inflate(R.layout.Horizental_item, parent, false))
    }

    override fun onBindViewHolder(holder: HorizentalItemViewHolder, position: Int) {
        val horizentalModel: HorizntalModel = postList[position]
        holder.textViewTitle.setText(HorizntalModel.getName())
        holder.itemView.setOnClickListener(
        {
            @Override
            fun onClick(v: View) {
                Toast.makeText(context,HorizntalModel.getName(),Toast.LENGTH_SHORT).show()
            }


        })
    }
    override fun getItemCount(): Int {
        return HorizntalModel.size()
    }
    fun postarImageData(moviesDetails: MoviesDetails)
    {
        Picasso.get().load("https://api.themoviedb.org/3/${MoviesDetails.poster_path}").into()
    }

}