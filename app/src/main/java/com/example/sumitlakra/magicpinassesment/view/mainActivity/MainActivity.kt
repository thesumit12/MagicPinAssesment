package com.example.sumitlakra.magicpinassesment.view.mainActivity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.crashlytics.android.Crashlytics
import com.example.sumitlakra.magicpinassesment.R
import com.example.sumitlakra.magicpinassesment.createProgressBar
import com.example.sumitlakra.magicpinassesment.data.model.Movie
import com.example.sumitlakra.magicpinassesment.numColumns
import com.example.sumitlakra.magicpinassesment.view.DetailActivity
import com.example.sumitlakra.magicpinassesment.view.SearchActivity
import com.example.sumitlakra.magicpinassesment.view.base.BaseActivity
import com.example.sumitlakra.magicpinassesment.viewModel.MainViewModel
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, MovieAdapter.OnItemClickListener {

    var movieList = mutableListOf<Movie>()
    private lateinit var mAdapter:MovieAdapter

    val progressBar: ProgressBar by lazy { createProgressBar(findViewById(R.id.progress_root_layout)) }

    private val mMainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Fabric.with(this, Crashlytics())

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        setUpRecyclerView()

    }

    private fun setUpRecyclerView(){
        movie_recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,
                false)
        movie_recyclerView.layoutManager = GridLayoutManager(this, numColumns())
        movie_recyclerView.itemAnimator = DefaultItemAnimator()
        mAdapter = MovieAdapter(movieList as ArrayList<Movie>, this, this)
        movie_recyclerView.adapter = mAdapter
        showLoading(progressBar)
        mMainViewModel.getUpcomingMovies().observe(this, Observer {
            hideLoading()
            movieList.addAll(it!!)
            mAdapter.notifyDataSetChanged()
            mMainViewModel.saveMovies(it)
        })
    }

    override fun onItemClick(position: Int) {
        val intent = DetailActivity.newIntent(this@MainActivity, movieList[position].id)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_popular -> {
                showLoading(progressBar)
                mMainViewModel.getPopularMovies().observe(this, Observer {
                    hideLoading()
                    movieList.clear()
                    movieList.addAll(it!!)
                    mAdapter.notifyDataSetChanged()
                    mMainViewModel.saveMovies(it)
                })
            }
            R.id.nav_top -> {
                showLoading(progressBar)
                mMainViewModel.getTopRatedMovies().observe(this, Observer {
                    hideLoading()
                    movieList.clear()
                    movieList.addAll(it!!)
                    mAdapter.notifyDataSetChanged()
                })
            }
            R.id.nav_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return false
    }
}
