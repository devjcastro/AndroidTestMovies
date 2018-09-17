package com.devjcastro.androidtestmovies.features.movie.detail.sections

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MyPagerAdapter(fm: FragmentManager, filmId: Long) : FragmentPagerAdapter(fm) {

    var filmId: Long = 0

    init {
        this.filmId = filmId
    }
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
               var fragment = DetailFragment()
                fragment.filmId = this.filmId
                fragment.initData()
                return fragment
            }
            else -> {
                return ListMoviesFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Detalle"
            else -> {
                return "Videos"
            }
        }
    }
}