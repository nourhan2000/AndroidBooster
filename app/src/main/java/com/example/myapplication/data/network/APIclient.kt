package com.example.myapplication.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIclient {

    private var retro: Retrofit? = null

    fun getClient(): Retrofit {

        if (retro==null)
            retro = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retro!!
    }
}