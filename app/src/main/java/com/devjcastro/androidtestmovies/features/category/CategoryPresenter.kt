package com.devjcastro.androidtestmovies.features.category

import com.devjcastro.androidtestmovies.data.db.entities.CategoryEntity
import com.devjcastro.androidtestmovies.di.category.CategoryModule
import com.devjcastro.androidtestmovies.di.category.DaggerCategoryComponent
import com.intergrupo.pruebaintergrupo.base.mvp.BasePresenter
import com.intergrupo.pruebaintergrupo.base.mvp.IBasePresenter
import javax.inject.Inject

interface ICategoryPresenter: IBasePresenter<ICategoryView> {
    fun dispatchCategories()
}

class CategoryPresenter: BasePresenter<ICategoryView>, ICategoryPresenter, CategoryInteractor.CallbackInteractor {

    @Inject
    lateinit var categoryModel: ICategoryInteractor

    constructor() {
        DaggerCategoryComponent.builder().categoryModule(CategoryModule(this)).build().inject(this)
    }


    override fun dispatchCategories() {
        categoryModel.listCategories()
    }

    override fun onSuccessListCategories(categories: List<CategoryEntity>) {
        view?.loadCategories(categories)
    }
}