package com.example.sumitlakra.magicpinassesment.view.mainActivity

import android.content.Context
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.sumitlakra.magicpinassesment.R
import com.example.sumitlakra.magicpinassesment.data.model.Movie
import com.example.sumitlakra.magicpinassesment.loadImageFromUrl
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(private val movieList: ArrayList<Movie>, private val context: Context,
                   private val mClickListener: OnItemClickListener) :
        RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Movie = movieList[position]

        context.loadImageFromUrl(movie.image_url, holder.image)

        holder.image.setOnClickListener { mClickListener.onItemClick(position) }
    }


    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val image: ImageView = view.movie_image
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}