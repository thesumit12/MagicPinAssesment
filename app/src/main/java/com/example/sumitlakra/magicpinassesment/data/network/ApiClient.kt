package com.example.sumitlakra.magicpinassesment.data.network

import com.example.sumitlakra.magicpinassesment.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var RETROFIT_INSTANCE: Retrofit? = null

    fun getClient(): Retrofit{
        if (RETROFIT_INSTANCE == null){
            RETROFIT_INSTANCE = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return RETROFIT_INSTANCE!!
    }

}