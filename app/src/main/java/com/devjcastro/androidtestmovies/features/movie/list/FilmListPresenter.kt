package com.devjcastro.androidtestmovies.features.movie.list

import com.devjcastro.androidtestmovies.data.dtos.FilmDTO
import com.devjcastro.androidtestmovies.di.movie.list.DaggerFilmListComponent
import com.devjcastro.androidtestmovies.di.movie.list.FilmListModule
import com.intergrupo.pruebaintergrupo.base.mvp.BasePresenter
import com.intergrupo.pruebaintergrupo.base.mvp.IBasePresenter
import javax.inject.Inject

enum class FilmCategories {
    POPULAR, TOP_RATING, UPCOMING
}

interface IFilmListPresenter: IBasePresenter<IFilmListView> {
    fun dispatchFilms(category: FilmCategories)
    fun onSuccessListFilms(categories: List<FilmDTO>?)
}

class FilmListPresenter: BasePresenter<IFilmListView>, IFilmListPresenter, FilmListInteractor.CallbackInteractor {

    @Inject
    lateinit var filmListModel: IFilmListInteractor

    constructor() {
        DaggerFilmListComponent.builder().filmListModule(FilmListModule(this)).build().inject(this)
    }

    override fun dispatchFilms(category: FilmCategories) {
        view?.showLoader()
        when(category){
            FilmCategories.POPULAR -> filmListModel.loadPopularFilms()
            FilmCategories.TOP_RATING -> filmListModel.loadTopRatedFilms()
            FilmCategories.UPCOMING -> filmListModel.loadUpcomingFilms()
        }
    }

    override fun onSuccessListFilms(categories: List<FilmDTO>?) {

        if(categories == null || categories.isEmpty()){
            view?.showEmptyIcon()
        }
        else {
            view?.loadFilms(categories)
            view?.hideLoaderifExistData()
        }
    }
}