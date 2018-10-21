package com.example.sumitlakra.magicpinassesment.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.AsyncTask
import com.example.sumitlakra.magicpinassesment.data.local.MovieDao
import com.example.sumitlakra.magicpinassesment.data.local.MovieDataBase
import com.example.sumitlakra.magicpinassesment.data.model.Movie
import com.example.sumitlakra.magicpinassesment.data.model.MovieDetails
import com.example.sumitlakra.magicpinassesment.data.network.AppApiHelper

class AppDataManager(private val mApiHelper: AppApiHelper, private val movieDataBase: MovieDataBase) : DataManager {

    override fun getTopRatedMovies(): MutableLiveData<List<Movie>> {
        return mApiHelper.getTopRatedMovies()
    }

    override fun getUpcomingMovies(): MutableLiveData<List<Movie>> {
        return mApiHelper.getUpcomingMovies()
    }

    override fun getMovieDetails(id: Int): MutableLiveData<MovieDetails> {
        return mApiHelper.getMovieDetails(id)
    }

    override fun getPopularMovies(): MutableLiveData<List<Movie>> {
        return mApiHelper.getPopularMovies()
    }

    override fun searchMovies(query: String): MutableLiveData<List<Movie>> {
        return mApiHelper.searchMovies(query)
    }

    override fun getAllMovies(): LiveData<List<Movie>> {
        return movieDataBase.movieDao().getAllMovies()
    }

    override fun getMovieByName(title: String): LiveData<List<Movie>> {
        return movieDataBase.movieDao().getMovieByName(title)
    }

    override fun saveMovieDetails(movie: Movie) {
        object : InsertAsyncTask(movieDataBase.movieDao()){}.execute(movie)
    }

   open class InsertAsyncTask(private val movieDao: MovieDao) : AsyncTask<Movie, Void, Void>(){

        override fun doInBackground(vararg movie: Movie?): Void? {
            movieDao.saveMovieDetails(movie[0]!!)
            return null
        }
    }
}