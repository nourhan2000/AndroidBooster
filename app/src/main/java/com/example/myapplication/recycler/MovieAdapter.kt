package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.network.modules.MoviesDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.horizental_item.view.*

class MovieAdapter (private val postList :ArrayList<MoviesDetails>): RecyclerView.Adapter<MovieAdapter.MovieItemViewHolder>() {

    class MovieItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val textViewTitle:TextView =itemView.text_title_horizental
        val imageView:ImageView=itemView.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.horizental_item, parent, false))
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        movieModel =  postList[position]
        holder.textViewTitle.text = movieModel.OriginalTitle
        val photo = "https://image.tmdb.org/t/p/w500/${movieModel.PosterPath}"
        Picasso.get().load(photo).into(holder.imageView)
    }

    companion object{
        lateinit var movieModel: MoviesDetails
    }

    override fun getItemCount(): Int {
        return postList.size
    }

     interface OnImageListener{
         fun onImageClick(position:Int)
     }

}