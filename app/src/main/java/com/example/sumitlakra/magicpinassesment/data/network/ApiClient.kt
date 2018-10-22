package com.example.sumitlakra.magicpinassesment.data.network

import com.example.sumitlakra.magicpinassesment.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private var RETROFIT_INSTANCE: Retrofit? = null

    fun getClient(): Retrofit{
        if (RETROFIT_INSTANCE == null){
            RETROFIT_INSTANCE = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(createInterceptor())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return RETROFIT_INSTANCE!!
    }

    private fun createInterceptor(): OkHttpClient{
        val interceptor =  HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY;
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE;
        }

        return OkHttpClient.Builder()
                .readTimeout(1000, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
    }

}