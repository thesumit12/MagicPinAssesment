package com.example.sumitlakra.magicpinassesment.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDetails(@SerializedName("title") @Expose var title: String,
                        @SerializedName("id") @Expose var id: Int,
                        @SerializedName("overview") @Expose var overview: String,
                        @SerializedName("poster_path") @Expose var image_url: String,
                        @SerializedName("release_date") @Expose var date: String,
                        @SerializedName("tagline") @Expose var tagline: String,
                        @SerializedName("vote_average") @Expose var rating: String,
                        @SerializedName("runtime") @Expose var runtime: String)