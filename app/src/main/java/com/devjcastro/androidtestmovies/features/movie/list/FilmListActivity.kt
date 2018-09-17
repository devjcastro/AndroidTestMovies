package com.devjcastro.androidtestmovies.features.movie.list

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.devjcastro.androidtestmovies.R
import com.devjcastro.androidtestmovies.data.dtos.FilmDTO
import com.devjcastro.androidtestmovies.di.movie.list.DaggerFilmListComponent
import com.devjcastro.androidtestmovies.di.movie.list.FilmListModule
import com.devjcastro.androidtestmovies.features.adapters.FilmAdapter
import com.devjcastro.androidtestmovies.features.movie.detail.DetailFilmActivity
import com.devjcastro.androidtestmovies.utils.PreferencesUtils
import kotlinx.android.synthetic.main.activity_film_list.*
import javax.inject.Inject


class FilmListActivity : AppCompatActivity(), IFilmListView, FilmAdapter.DetailFilmListener {

    @Inject
    lateinit var presenter: IFilmListPresenter

    lateinit var adapter: FilmAdapter

    val REQUEST_CODE_SUCCESS = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_list)

        DaggerFilmListComponent.builder()
                .filmListModule(FilmListModule())
                .build().inject(this)

        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    fun initData(){
        presenter.attach(this@FilmListActivity)

        val bundle = intent.extras
        if (bundle != null) {
            val categoryId = bundle.getString(PreferencesUtils.KeyPreferences.CATEGORY_ID)
            val categoryName = bundle.getString(PreferencesUtils.KeyPreferences.CATEGORY_NAME)

            title = categoryName
            when(categoryId.toInt()){
                1 -> presenter.dispatchFilms(FilmCategories.POPULAR)
                2 -> presenter.dispatchFilms(FilmCategories.TOP_RATING)
                3 -> presenter.dispatchFilms(FilmCategories.UPCOMING)
            }
        }

        rvFilms.apply {
            layoutManager = LinearLayoutManager(this@FilmListActivity)
        }
    }

    override fun loadFilms(films: List<FilmDTO>) {
        adapter = FilmAdapter(films)
        adapter.setCallbackListener(this)
        rvFilms.adapter = adapter
    }

    override fun showLoader() {
        rvFilms.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        ivEmpty.visibility = View.GONE
    }

    override fun hideLoaderifExistData() {
        progressBar.visibility = View.GONE
        ivEmpty.visibility = View.GONE
        rvFilms.visibility = View.VISIBLE
    }

    override fun showEmptyIcon() {
        rvFilms.visibility = View.GONE
        progressBar.visibility = View.GONE
        ivEmpty.visibility = View.VISIBLE
    }

    override fun launchActivityForDetail(filmId: Long) {
        val intent = Intent(this, DetailFilmActivity::class.java)
        intent.putExtra(PreferencesUtils.KeyPreferences.FILM_ID, filmId.toString())
        startActivityForResult(intent, REQUEST_CODE_SUCCESS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SUCCESS){
            if(resultCode == Activity.RESULT_OK){
                hideLoaderifExistData()
            }
        }
    }
}
