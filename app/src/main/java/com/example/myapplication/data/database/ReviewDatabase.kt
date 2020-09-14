package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Review::class] , version=1, exportSchema = false)
abstract class ReviewDatabase : RoomDatabase(){

    abstract fun getReviewsDao() : ReviewsDao

    companion object{
        private var INSTANCE: ReviewDatabase?= null

        fun getDatabase(context: Context): ReviewDatabase {

            if(INSTANCE != null)
                return INSTANCE!!

            INSTANCE = Room.databaseBuilder(
                context.applicationContext, ReviewDatabase::class.java, "Reviews_db")
                .allowMainThreadQueries().build()

            return INSTANCE!!
        }
    }


}