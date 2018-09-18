package com.devjcastro.androidtestmovies.features.category

import com.devjcastro.androidtestmovies.R
import com.devjcastro.androidtestmovies.data.db.DBHelper
import com.devjcastro.androidtestmovies.data.db.entities.CategoryEntity
import com.devjcastro.androidtestmovies.utils.NetworkUtils
import java.util.ArrayList

interface ICategoryInteractor{
    fun listCategories()
}

class CategoryInteractor: ICategoryInteractor {

    interface CallbackInteractor {
        fun onSuccessListCategories(categories: List<CategoryEntity>)
    }

    var listener: CallbackInteractor? = null

    constructor(listener: CallbackInteractor) {
        this.listener = listener
    }

    override fun listCategories() {

        var categories: List<CategoryEntity>
        val db = DBHelper()

        if(NetworkUtils.isOnline()){

            categories = mutableListOf()
            categories.add(CategoryEntity(1, "Popular", R.drawable.popular_movie))
            categories.add(CategoryEntity(2, "Top Rated", R.drawable.toprated_movie))
            categories.add(CategoryEntity(3, "Upcoming", R.drawable.upcoming_movie))
            db.insertRows(categories)
            listener?.onSuccessListCategories(categories)

        }
        else {
            if (db.isNotEmptyTable(CategoryEntity::class.java)){
                db.getRows(CategoryEntity::class.java)?.let {
                    categories = it
                    listener?.onSuccessListCategories(categories)
                }
            }
        }

    }
}