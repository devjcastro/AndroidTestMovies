package com.devjcastro.androidtestmovies.features.category

import com.devjcastro.androidtestmovies.data.db.entities.CategoryEntity

interface ICategoryView {
    fun loadCategories(categories: List<CategoryEntity>)
}