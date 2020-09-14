package com.example.myapplication.data.database.Reviews

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Review (
    @PrimaryKey
    val ReviewId :Long,
    val movieReview: String
)