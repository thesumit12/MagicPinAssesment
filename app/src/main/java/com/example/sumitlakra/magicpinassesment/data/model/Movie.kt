package com.example.sumitlakra.magicpinassesment.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_table")
data class Movie(@PrimaryKey @SerializedName("title") @Expose var title: String,
                 @ColumnInfo @SerializedName("id") @Expose var id: Int,
                 @ColumnInfo @SerializedName("poster_path") @Expose var image_url: String){
    constructor():this("",0,"")
}