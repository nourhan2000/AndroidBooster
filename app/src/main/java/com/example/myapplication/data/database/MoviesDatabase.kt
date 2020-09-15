package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class] , version=2, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase(){

        abstract fun getMoviesDao() : MoviesDao

        companion object{
            private var INSTANCE: MoviesDatabase?= null

            fun getDatabase(context: Context): MoviesDatabase {

                if(INSTANCE != null)
                    return INSTANCE!!

                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, MoviesDatabase::class.java, "Movies_db")
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build()

                return INSTANCE!!
            }
        }
    }
