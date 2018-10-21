package com.example.sumitlakra.magicpinassesment.data.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.example.sumitlakra.magicpinassesment.data.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * from movie_table")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * from movie_table WHERE title LIKE :title")
    fun getMovieByName(title: String): LiveData<List<Movie>>

    @Insert(onConflict = REPLACE)
    fun saveMovieDetails(movie: Movie)
}