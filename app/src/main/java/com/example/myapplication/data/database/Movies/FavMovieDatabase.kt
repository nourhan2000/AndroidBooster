package com.example.myapplication.data.database.Movies

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class] , version=2, exportSchema = false)

abstract class FavMovieDatabase : RoomDatabase() {

    abstract fun getFavMovieDao() : FavoriteDao
    companion object{
        private var INSTANCE: FavMovieDatabase?= null

        fun getDatabase(context: Context): FavMovieDatabase {

            if(INSTANCE != null)
                return INSTANCE!!

            INSTANCE = Room.databaseBuilder(
                context.applicationContext, FavMovieDatabase::class.java , "FavMovies_db")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build()

            return INSTANCE!!
        }
    }
}