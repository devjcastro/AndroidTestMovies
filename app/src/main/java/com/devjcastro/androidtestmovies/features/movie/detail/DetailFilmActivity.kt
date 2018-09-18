package com.devjcastro.androidtestmovies.features.movie.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.devjcastro.androidtestmovies.R
import com.devjcastro.androidtestmovies.data.dtos.YoutubeVideoDTO
import com.devjcastro.androidtestmovies.features.movie.detail.sections.MyPagerAdapter
import com.devjcastro.androidtestmovies.utils.PreferencesUtils
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_detail_film.*


class DetailFilmActivity : AppCompatActivity() {

    companion object {
        var subject: PublishSubject<List<YoutubeVideoDTO>>? = PublishSubject.create()
    }

    val REQUEST_CODE_SUCCESS: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)

        initData()
    }

    fun initData(){
        title = "Detall de la Película"
        val bundle = intent.extras
        if (bundle != null) {
            val filmId = bundle.getString(PreferencesUtils.KeyPreferences.FILM_ID)

            val fragmentAdapter = MyPagerAdapter(supportFragmentManager, filmId.toLong())
            viewpager_main.adapter = fragmentAdapter
            tabs_main.setupWithViewPager(viewpager_main)
        }
    }

}
