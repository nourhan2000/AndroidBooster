package com.example.myapplication.data.database.Videos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Video::class] , version=1, exportSchema = false)
abstract class VideoDatabase : RoomDatabase(){

    abstract fun getVidsDao() : VideosDao

    companion object{
        private var INSTANCE: VideoDatabase?= null

        fun getDatabase(context: Context): VideoDatabase {

            if(INSTANCE != null)
                return INSTANCE!!

            INSTANCE = Room.databaseBuilder(
                context.applicationContext, VideoDatabase::class.java, "Videos_db")
                .allowMainThreadQueries().build()

            return INSTANCE!!
        }
    }
}