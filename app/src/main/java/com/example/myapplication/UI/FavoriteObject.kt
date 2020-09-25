package com.example.myapplication.UI

import android.content.Context
import com.example.myapplication.data.database.Movies.FavMovieDatabase
import com.example.myapplication.data.database.Movies.Movie
import java.util.*

object FavoriteObject {
    private lateinit var favMovieDatabase: FavMovieDatabase
    var favMovies = LinkedList<Movie>()

    fun createFavDb(context: Context){
        favMovieDatabase= FavMovieDatabase.getDatabase(context)
    }

    fun findMovieByID(popMovies:List<Movie>?,topMovies:List<Movie>?,movieID: Long,isFav:Boolean):Movie?{
        var movie:Movie? = null
        popMovies?.forEach {
            if (movieID==it.movieId){
                it.isFavorite=isFav
                movie= it
            }
        }
        if(movie==null){
            topMovies?.forEach {
                if (movieID==it.movieId){
                    it.isFavorite=isFav
                    movie= it
                }
            }
        }
        return movie
    }

    fun addFav(movie: Movie){
        favMovies.add(movie)
    }

    fun removeFav(movie: Movie){
        favMovies.remove(movie)
    }


}