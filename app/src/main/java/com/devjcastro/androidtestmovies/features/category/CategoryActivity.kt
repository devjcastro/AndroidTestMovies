package com.devjcastro.androidtestmovies

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.devjcastro.androidtestmovies.data.db.entities.CategoryEntity
import com.devjcastro.androidtestmovies.di.category.CategoryModule
import com.devjcastro.androidtestmovies.di.category.DaggerCategoryComponent
import com.devjcastro.androidtestmovies.features.adapters.CategoryAdapter
import com.devjcastro.androidtestmovies.features.category.ICategoryPresenter
import com.devjcastro.androidtestmovies.features.category.ICategoryView
import kotlinx.android.synthetic.main.activity_category.*
import javax.inject.Inject

class CategoryActivity : AppCompatActivity(), ICategoryView {

    @Inject
    lateinit var presenter: ICategoryPresenter

    lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        title = "Categorías"

        DaggerCategoryComponent.builder()
                .categoryModule(CategoryModule())
                .build().inject(this)

        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    fun initData(){

        presenter.attach(this)
        rvCategories.apply {
            layoutManager = LinearLayoutManager(this@CategoryActivity)
        }
        presenter.dispatchCategories()
    }

    override fun loadCategories(categories: List<CategoryEntity>) {
        adapter = CategoryAdapter(categories)
        rvCategories.adapter = adapter
        rvCategories.visibility = View.VISIBLE
        ivEmpty.visibility = View.GONE
    }
}
