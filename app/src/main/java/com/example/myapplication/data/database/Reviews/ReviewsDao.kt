package com.example.myapplication.data.database.Reviews

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReviewsDao {

    @Query("Select * FROM Review")
    fun getReviews(): List<Review>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addReviews(Reviews: List<Review>)
}