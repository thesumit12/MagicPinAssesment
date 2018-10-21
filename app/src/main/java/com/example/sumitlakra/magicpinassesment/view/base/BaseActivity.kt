package com.example.sumitlakra.magicpinassesment.view.base

import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.sumitlakra.magicpinassesment.R

abstract class BaseActivity() : AppCompatActivity() {

    private var mProgressDialog: ProgressBar? = null

    fun showLoading(progressBar: ProgressBar) {
        hideLoading()
        mProgressDialog = progressBar
        mProgressDialog!!.visibility = View.VISIBLE
    }

    //abstract fun createProgressBar(): ProgressBar

    fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.visibility == View.VISIBLE)
            mProgressDialog!!.visibility = View.GONE

    }

    private fun showSnackBar(message: String){
        val snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT)
        val sbView = snackbar.view
        val textView = sbView
                .findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        snackbar.show()
    }

    fun onError(message: String?) {
        if (message != null) {
            showSnackBar(message)
        } else {
            showSnackBar(getString(R.string.some_error))
        }
    }

    fun onError(resId: Int) {
        onError(getString(resId))
    }

    fun showMessage(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show()
        }
    }

    fun showMessage(resId: Int) {
        showMessage(getString(resId))
    }

    fun isNetworkConnected(): Boolean {
        return false
    }
}