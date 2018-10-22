package com.example.sumitlakra.magicpinassesment.viewModel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.sumitlakra.magicpinassesment.data.model.Movie
import com.example.sumitlakra.magicpinassesment.data.model.MovieDetails
import com.example.sumitlakra.magicpinassesment.view.base.BaseViewModel

class MainViewModel(application: Application) : BaseViewModel(application) {

    private var movies: MutableLiveData<List<Movie>>? = null
    private var popularMovieList: MutableLiveData<List<Movie>>? = null
    private var upComingMovieList: MutableLiveData<List<Movie>>? = null

    fun getTopRatedMovies(): LiveData<List<Movie>>{
        if (movies == null){
            movies = getDataManager().getTopRatedMovies()
        }
        return movies!!
    }

    fun getPopularMovies(): LiveData<List<Movie>>{
        if (popularMovieList == null){
            popularMovieList = getDataManager().getPopularMovies()
        }
        return popularMovieList!!
    }

    fun getUpcomingMovies(): LiveData<List<Movie>>{
        if (upComingMovieList == null){
            upComingMovieList = getDataManager().getUpcomingMovies()
        }
        return upComingMovieList!!
    }

    fun saveMovies( movieList: List<Movie>){
        for (movie in movieList)
            getDataManager().saveMovieDetails(movie)
    }
}