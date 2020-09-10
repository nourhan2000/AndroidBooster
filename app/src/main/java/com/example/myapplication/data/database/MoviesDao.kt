package com.example.myapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovies(Movies: List<Movie>)

    @Query("Select * FROM Movie")
    fun getMovies(): List<Movie>
}