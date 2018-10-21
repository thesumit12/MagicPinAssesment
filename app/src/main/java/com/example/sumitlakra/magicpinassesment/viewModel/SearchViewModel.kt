package com.example.sumitlakra.magicpinassesment.viewModel

import android.app.Application
import android.arch.lifecycle.LiveData
import com.example.sumitlakra.magicpinassesment.data.model.Movie
import com.example.sumitlakra.magicpinassesment.data.model.MovieDetails
import com.example.sumitlakra.magicpinassesment.view.base.BaseViewModel

class SearchViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var movies: LiveData<List<Movie>>

    fun getMoviesFromDb():LiveData<List<Movie>>{
        if (!::movies.isInitialized){
            movies = getDataManager().getAllMovies()
        }
        return movies
    }

    fun searchMovie(query: String):LiveData<List<Movie>>{
        return getDataManager().searchMovies(query)
    }
}