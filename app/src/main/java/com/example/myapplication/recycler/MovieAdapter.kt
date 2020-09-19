package com.example.myapplication.recycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity2
import com.example.myapplication.R
import com.example.myapplication.data.database.Movies.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.horizental_item.view.*

class MovieAdapter (private val postList :List<Movie>): RecyclerView.Adapter<MovieAdapter.MovieItemViewHolder>() {

    class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.text_title_horizental
        val imageView: ImageView = itemView.image
        val favButton: Button = itemView.fav_button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.horizental_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        var movieModel = postList[position]
        holder.textViewTitle.text = movieModel.OriginalTitle
        val photo = "https://image.tmdb.org/t/p/w500/${movieModel.PosterPath}"
        Picasso.get().load(photo).into(holder.imageView)
        holder.imageView.setOnClickListener() {
            val intent = Intent(holder.itemView.context, MainActivity2::class.java)
            intent.putExtra("photo", photo)
            intent.putExtra("overView", movieModel.overview)
            intent.putExtra("date", movieModel.releaseDate)
            intent.putExtra("vote", movieModel.voteAverage.toString())
            holder.itemView.context.startActivity(intent)
        }

        holder.favButton.setOnClickListener {
            if (movieModel.isFavorite == false) {
                movieModel.isFavorite = true
                holder.favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
            } else {
                holder.favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
                movieModel.isFavorite = false
            }
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

}