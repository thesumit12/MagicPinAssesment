package com.example.sumitlakra.magicpinassesment.view.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import com.example.sumitlakra.magicpinassesment.data.AppDataManager
import com.example.sumitlakra.magicpinassesment.data.DataManager
import com.example.sumitlakra.magicpinassesment.data.local.MovieDataBase
import com.example.sumitlakra.magicpinassesment.data.network.AppApiHelper

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    fun getDataManager(): DataManager{
        val dataBase = MovieDataBase.getInstance(getApplication())
        return AppDataManager(AppApiHelper(), dataBase!!)
    }
}