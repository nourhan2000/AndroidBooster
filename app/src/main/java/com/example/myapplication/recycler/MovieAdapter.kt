package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.UI.FavouritesFragmentDirections
import com.example.myapplication.UI.PopularMoviesFragmentDirections
import com.example.myapplication.UI.TopMoviesFragmentDirections
import com.example.myapplication.data.database.Movies.FavMovieDatabase
import com.example.myapplication.data.database.Movies.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.horizental_item.view.*

class MovieAdapter (private var postList :List<Movie>): RecyclerView.Adapter<MovieAdapter.MovieItemViewHolder>() {

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
        val favMovieDatabase=FavMovieDatabase.getDatabase(holder.itemView.context)
        var movieModel = postList[position]
        holder.textViewTitle.text = movieModel.OriginalTitle
        val photo = "https://image.tmdb.org/t/p/w500/${movieModel.PosterPath}"
        Picasso.get().load(photo).into(holder.imageView)
        if(movieModel.isFavorite)
            holder.favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        else
            holder.favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)

        holder.imageView.setOnClickListener() {
            when(type){
                "fav" ->{
                    val action= FavouritesFragmentDirections.actionBlankFragment2ToBlankFragment4(movieModel.movieId,photo,movieModel.releaseDate,movieModel.overview,movieModel.voteAverage.toString())
                    findNavController(holder.itemView).navigate(action)
                }
                "top"->{
                    val action = TopMoviesFragmentDirections.actionBlankFragment3ToBlankFragment4(movieModel.movieId,photo,movieModel.releaseDate,movieModel.overview,movieModel.voteAverage.toString())
                    findNavController(holder.itemView).navigate(action)
                }
                "pop"->{
                    val action = PopularMoviesFragmentDirections.actionBlankFragmentToBlankFragment4(movieModel.movieId,photo,movieModel.releaseDate,movieModel.overview,movieModel.voteAverage.toString())
                    findNavController(holder.itemView).navigate(action)
                }
            }
        }

        holder.favButton.setOnClickListener {
            if (movieModel.isFavorite == false) {
                movieModel.isFavorite = true
                holder.favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                favMovieDatabase.getFavMovieDao().addMovie(movieModel)
            } else {
                holder.favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
                movieModel.isFavorite = false
                favMovieDatabase.getFavMovieDao().deleteMovie(movieModel)
            }
        }
    }
    companion object{
        lateinit var type: String
    }

    override fun getItemCount(): Int {
        return postList.size
    }

}