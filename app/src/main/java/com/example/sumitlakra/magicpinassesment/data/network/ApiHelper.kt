package com.example.sumitlakra.magicpinassesment.data.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.sumitlakra.magicpinassesment.data.model.Movie
import com.example.sumitlakra.magicpinassesment.data.model.MovieDetails

interface ApiHelper {

    fun getTopRatedMovies(): MutableLiveData<List<Movie>>

    fun getUpcomingMovies(): MutableLiveData<List<Movie>>

    fun getPopularMovies(): MutableLiveData<List<Movie>>

    fun searchMovies(query: String): MutableLiveData<List<Movie>>

    fun getMovieDetails(id: Int): MutableLiveData<MovieDetails>
}