package com.example.myapplication.data.database.Movies

import androidx.room.*

@Dao
interface FavoriteDao {
    @Query("Select * FROM Movie")
    fun getMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovies(Movies: List<Movie>)

    @Delete
    fun deleteMovie(movie: Movie)

}