package com.example.myapplication.data.database.Videos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VideosDao {
    @Query("Select * FROM Video")
    fun getVids(): List<Video>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addVids(Movies: List<Video>)
}