package com.example.myapplication.data.database.Movies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Query("Select * FROM Movie  WHERE type='pop'")
    fun getMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovies(Movies: List<Movie>)

 }