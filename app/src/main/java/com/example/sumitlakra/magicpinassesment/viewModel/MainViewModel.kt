package com.example.sumitlakra.magicpinassesment.viewModel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.sumitlakra.magicpinassesment.data.model.Movie
import com.example.sumitlakra.magicpinassesment.data.model.MovieDetails
import com.example.sumitlakra.magicpinassesment.view.base.BaseViewModel

class MainViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var movies: MutableLiveData<List<Movie>>
    private lateinit var popularMovieList: MutableLiveData<List<Movie>>
    private lateinit var upComingMovieList: MutableLiveData<List<Movie>>

    fun getTopRatedMovies(): LiveData<List<Movie>>{
        if (!::movies.isInitialized){
            movies = getDataManager().getTopRatedMovies()
        }
        return movies
    }

    fun getPopularMovies(): LiveData<List<Movie>>{
        if (!::popularMovieList.isInitialized){
            popularMovieList = getDataManager().getPopularMovies()
        }
        return popularMovieList
    }

    fun getUpcomingMovies(): LiveData<List<Movie>>{
        if (!::upComingMovieList.isInitialized){
            upComingMovieList = getDataManager().getUpcomingMovies()
        }
        return upComingMovieList
    }

    fun saveMovies( movieList: List<Movie>){
        for (movie in movieList)
            getDataManager().saveMovieDetails(movie)
    }
}