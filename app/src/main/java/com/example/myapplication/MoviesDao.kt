package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.Movie

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovies(Movies: List<Movie>)

    @Query("Select * FROM Movie")
    fun getMovies(Movies: List<Movie>)
}