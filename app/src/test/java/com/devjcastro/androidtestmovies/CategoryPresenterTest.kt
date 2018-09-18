package com.devjcastro.androidtestmovies

import com.devjcastro.androidtestmovies.data.db.entities.CategoryEntity
import com.devjcastro.androidtestmovies.features.category.ICategoryPresenter
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoryPresenterTest {

    @Mock
    var categoryPresenter: ICategoryPresenter? = null

    @Test
    fun loadCategoriesSuccess() {

        val list = listOf<CategoryEntity>()
        Mockito.doNothing().`when`(categoryPresenter)?.dispatchCategories()
        Mockito.doNothing().`when`(categoryPresenter)?.onSuccessListCategories(anyList())

        categoryPresenter?.dispatchCategories()
        categoryPresenter?.onSuccessListCategories(list)

        Mockito.verify(categoryPresenter, Mockito.times(1))?.onSuccessListCategories(list)
    }
}