package com.example.sumitlakra.magicpinassesment.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.sumitlakra.magicpinassesment.R
import com.example.sumitlakra.magicpinassesment.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private val mDetailViewModel: DetailViewModel by lazy {
        ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.extras.getInt(DetailActivity.ID)

        mDetailViewModel.getMovieDetails(id).observe(this, Observer {
            if (it != null){
                tv_title.text = it.title
                tv_tagline.text = it.tagline
                tv_date.text = it.date
                tv_overview.text = it.overview
                tv_runtime.text = it.runtime+" mins"

                Glide.with(this).load("https://image.tmdb.org/t/p/original${it.image_url}")
                        .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                                .override(1200, 200))
                        .into(iv_detail)
            }
        })
    }

    companion object {
        private const val ID = "id"

        fun newIntent(context: Context, id:Int): Intent{
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(ID,id)
            return intent
        }
    }
}
