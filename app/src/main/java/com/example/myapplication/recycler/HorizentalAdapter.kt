package com.example.myapplication.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.MainActivity2
import com.example.myapplication.R
import com.example.myapplication.network.modules.MoviesDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.horizental_item.view.*

class HorizentalAdapter ( private val postList :ArrayList<MoviesDetails>): RecyclerView.Adapter<HorizentalAdapter.HorizentalItemViewHolder>() {

    class HorizentalItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val textViewTitle:TextView =itemView.text_title_horizental
        val imageView:ImageView=itemView.image

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizentalItemViewHolder {
        return HorizentalItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.horizental_item, parent, false))
    }

    override fun onBindViewHolder(holder: HorizentalItemViewHolder, position: Int) {
        horizentalModel = postList[position]
        holder.textViewTitle.text = horizentalModel.OriginalTitle
        val photo = "https://api.themoviedb.org/3/w500/${horizentalModel.PosterPath}"
        Picasso.get().load(photo).into(holder.imageView)

    }
    companion object{
        lateinit var horizentalModel: MoviesDetails
    }
    override fun getItemCount(): Int {
        return postList.size
    }


}