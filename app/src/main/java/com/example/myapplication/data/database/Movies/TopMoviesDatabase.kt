package com.example.myapplication.data.database.Movies

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class] , version=3, exportSchema = false)
abstract class TopMoviesDatabase :RoomDatabase() {

    abstract fun getTopMoviesDao() : TopMoviesDao

    companion object{
        private var INSTANCE: TopMoviesDatabase?= null

        fun getDatabase(context: Context): TopMoviesDatabase {

            if(INSTANCE != null)
                return INSTANCE!!

            INSTANCE = Room.databaseBuilder(
                context.applicationContext,TopMoviesDatabase::class.java, "TopMovies_db")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build()

            return INSTANCE!!
        }
    }

}