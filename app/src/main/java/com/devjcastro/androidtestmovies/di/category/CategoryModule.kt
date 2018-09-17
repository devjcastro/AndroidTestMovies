package com.devjcastro.androidtestmovies.di.category

import com.devjcastro.androidtestmovies.features.category.CategoryInteractor
import com.devjcastro.androidtestmovies.features.category.CategoryPresenter
import com.devjcastro.androidtestmovies.features.category.ICategoryInteractor
import com.devjcastro.androidtestmovies.features.category.ICategoryPresenter
import dagger.Module
import dagger.Provides

@Module
class CategoryModule {

    lateinit var interactorListener: CategoryInteractor.CallbackInteractor

    constructor()

    constructor(listener: CategoryInteractor.CallbackInteractor){
        interactorListener = listener
    }

    @Provides
    fun provideCategoryPresenter(): ICategoryPresenter {
        return CategoryPresenter()
    }

    @Provides
    fun provideLoginModel(): ICategoryInteractor {
        return CategoryInteractor(interactorListener)
    }
}