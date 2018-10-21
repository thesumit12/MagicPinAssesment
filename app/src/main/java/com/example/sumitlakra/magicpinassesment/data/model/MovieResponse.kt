package com.example.sumitlakra.magicpinassesment.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse(@SerializedName("page") @Expose val page: Int,
                         @SerializedName("total_results") @Expose val total_results: Int,
                         @SerializedName("results") @Expose val results: List<Movie>,
                         @SerializedName("total_pages") @Expose val total_pages: Int)