package com.example.myapplication.data.database.Movies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TopMoviesDao {

    @Query("Select * FROM Movie")
    fun getTopMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTopMovies(topMovies: List<Movie>)
}