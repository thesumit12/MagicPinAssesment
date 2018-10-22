package com.example.sumitlakra.magicpinassesment

import android.content.Context
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun Context.createProgressBar(layout: RelativeLayout): ProgressBar{
    val progressBar = ProgressBar(this, null, android.R.attr.progressBarStyleLarge)
    val params = RelativeLayout.LayoutParams(300, 300)
    params.addRule(RelativeLayout.CENTER_IN_PARENT)
    layout.addView(progressBar, params)
    progressBar.isIndeterminate = true
    return progressBar
}

fun Context.loadImageFromUrl(image_url: String, imageView: ImageView){
    Glide.with(this).load("https://image.tmdb.org/t/p/original$image_url")
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .override(600, 200)
                    .error(R.mipmap.ic_error_round))
            .into(imageView)
}

fun Context.numColumns(): Int{
    val displayMetrics = this.resources.displayMetrics
    val width:Float = displayMetrics.widthPixels / displayMetrics.density
    return (width/180).toInt()
}