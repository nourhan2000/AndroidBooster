package com.example.myapplication.data.database.Movies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Query("Select * FROM Movie WHERE isFavorite = 'true' ")
    fun getMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovies(Movies: List<Movie>)

}