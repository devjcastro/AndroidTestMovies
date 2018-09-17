package com.devjcastro.androidtestmovies.di.category

import com.devjcastro.androidtestmovies.CategoryActivity
import com.devjcastro.androidtestmovies.features.category.CategoryInteractor
import com.devjcastro.androidtestmovies.features.category.CategoryPresenter
import dagger.Component

@Component(modules = arrayOf(CategoryModule::class))
interface CategoryComponent {
    fun inject(categoryActivity: CategoryActivity)
    fun inject(categoryPresenter: CategoryPresenter)
    fun inject(categoryModel: CategoryInteractor)
    fun inject(categoryListenerPresenter: CategoryInteractor.CallbackInteractor)
}