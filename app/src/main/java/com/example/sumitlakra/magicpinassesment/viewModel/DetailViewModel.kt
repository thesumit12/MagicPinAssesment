package com.example.sumitlakra.magicpinassesment.viewModel

import android.app.Application
import android.arch.lifecycle.LiveData
import com.example.sumitlakra.magicpinassesment.data.model.Movie
import com.example.sumitlakra.magicpinassesment.data.model.MovieDetails
import com.example.sumitlakra.magicpinassesment.view.base.BaseViewModel

class DetailViewModel(application: Application) : BaseViewModel(application) {

    fun getMovieDetails(id: Int): LiveData<MovieDetails> {
        return getDataManager().getMovieDetails(id)
    }
}