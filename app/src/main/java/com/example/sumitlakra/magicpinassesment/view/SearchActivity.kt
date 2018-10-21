package com.example.sumitlakra.magicpinassesment.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.sumitlakra.magicpinassesment.R
import com.example.sumitlakra.magicpinassesment.viewModel.SearchViewModel
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    var movieMap = hashMapOf<String, Int>()

    private val mSearchViewModel: SearchViewModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        search_view.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                if (movieMap.containsKey(newText)){
                    val id: Int = movieMap[newText!!]!!
                    startDetailActivity(id)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (movieMap.containsKey(query!!)){
                    val id = movieMap[query]!!
                    startDetailActivity(id)
                }
                else{
                    mSearchViewModel.searchMovie(query).observe(this@SearchActivity, Observer {
                        if(it != null){
                            val id: Int = it[0].id
                            startDetailActivity(id)
                        }
                    })
                }
                return true
            }
        })

        mSearchViewModel.getMoviesFromDb().observe(this, Observer {
            val movieArray = arrayOfNulls<String>(it!!.size)
                for ((i, movie) in it.withIndex()){
                    movieArray[i] = movie.title
                    movieMap[movie.title] = movie.id
                }
                search_view.setSuggestions(movieArray)

        })

        search_view.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener{
            override fun onSearchViewShown() {
                search_view.visibility = View.VISIBLE
            }

            override fun onSearchViewClosed() {

            }
        })
    }

    fun startDetailActivity(id: Int){
        val intent = DetailActivity.newIntent(this@SearchActivity, id)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
       // Inflate the menu; this adds items to the action bar if it is present.
       menuInflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.action_search)
        search_view.setMenuItem(item)
       return true
   }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.e("SA","Inside selected")
        return when (item.itemId) {
            R.id.action_search -> {
                Log.e("SA","search clicked")
                search_view.visibility = View.VISIBLE
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (search_view.isSearchOpen) {
            search_view.closeSearch()
        } else {
            super.onBackPressed()
        }
    }
}
