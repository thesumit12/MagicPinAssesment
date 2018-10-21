package com.example.sumitlakra.magicpinassesment.data

import com.example.sumitlakra.magicpinassesment.data.local.MovieDao
import com.example.sumitlakra.magicpinassesment.data.network.ApiHelper

interface DataManager : ApiHelper, MovieDao {
}