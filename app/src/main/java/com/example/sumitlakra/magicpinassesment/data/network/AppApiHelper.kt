package com.example.sumitlakra.magicpinassesment.data.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.sumitlakra.magicpinassesment.BuildConfig
import com.example.sumitlakra.magicpinassesment.data.model.Movie
import com.example.sumitlakra.magicpinassesment.data.model.MovieDetails
import com.example.sumitlakra.magicpinassesment.data.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppApiHelper : ApiHelper {

    private val apiService: ApiInterface by lazy {
        ApiClient.getClient().create(ApiInterface::class.java)
    }

    override fun getTopRatedMovies(): MutableLiveData<List<Movie>> {
        val call: Call<MovieResponse> = apiService.getTopRatedMovies(BuildConfig.ApiKey)
        return makeNetworkCall(call)
    }

    override fun getUpcomingMovies(): MutableLiveData<List<Movie>> {
        val call: Call<MovieResponse> = apiService.getUpComingMovies(BuildConfig.ApiKey)
        return makeNetworkCall(call)
    }

    override fun getMovieDetails(id: Int): MutableLiveData<MovieDetails> {
        val data = MutableLiveData<MovieDetails>()
        val call: Call<MovieDetails> = apiService.getMovieDetails(id,BuildConfig.ApiKey)

        call.enqueue(object : Callback<MovieDetails>{

            override fun onResponse(call: Call<MovieDetails>?, response: Response<MovieDetails>?) {
                if (response!!.isSuccessful){
                    val movieResponse = response.body()
                    if (movieResponse != null){
                        data.value = movieResponse
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetails>?, t: Throwable?) {
                data.value = null
                Log.e("AppApiHelper"," "+t?.message)
            }
        })
        return data
    }

    override fun getPopularMovies(): MutableLiveData<List<Movie>> {
        val call: Call<MovieResponse> = apiService.getPopularMovies(BuildConfig.ApiKey)
        return makeNetworkCall(call)
    }

    override fun searchMovies(query: String): MutableLiveData<List<Movie>> {

        val call: Call<MovieResponse> = apiService.searchMovie(BuildConfig.ApiKey, query)
        return makeNetworkCall(call)
    }

    private fun makeNetworkCall(call: Call<MovieResponse>): MutableLiveData<List<Movie>>{
        val data = MutableLiveData<List<Movie>>()

        call.enqueue(object : Callback<MovieResponse>{

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                if (response!!.isSuccessful){
                    val movieResponse = response.body()
                    if (movieResponse != null){
                        data.value = movieResponse.results
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                data.value = null
                Log.e("AppApiHelper"," "+t?.message)
            }
        })
        return data
    }


}