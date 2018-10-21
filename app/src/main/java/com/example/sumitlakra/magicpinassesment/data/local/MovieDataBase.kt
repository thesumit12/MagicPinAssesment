package com.example.sumitlakra.magicpinassesment.data.local

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.Database
import android.arch.persistence.room.DatabaseConfiguration
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.sumitlakra.magicpinassesment.data.model.Movie
import com.example.sumitlakra.magicpinassesment.data.model.MovieDetails

@Database(entities = [(Movie::class)], version = 1)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        private var INSTANCE: MovieDataBase? = null

        fun getInstance(context: Context): MovieDataBase?{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MovieDataBase::class.java, "movie_detail_db")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}