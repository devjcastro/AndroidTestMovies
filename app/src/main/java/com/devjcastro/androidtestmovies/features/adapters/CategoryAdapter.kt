package com.devjcastro.androidtestmovies.features.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devjcastro.androidtestmovies.R
import com.devjcastro.androidtestmovies.data.db.entities.CategoryEntity
import com.devjcastro.androidtestmovies.features.movie.list.FilmListActivity
import com.devjcastro.androidtestmovies.utils.PreferencesUtils
import kotlinx.android.synthetic.main.item_categories_list.view.*

class CategoryAdapter(private val data: List<CategoryEntity>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private var context: Context? = null
    private var inflater: LayoutInflater? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        context = parent.context
        inflater = LayoutInflater.from(context)

        val view = inflater?.inflate(R.layout.item_categories_list, parent, false)
        return CategoryViewHolder(view!!)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = data[position]
        holder.tvCategoryId.text = category.id.toString()
        holder.categoryTitle.text = category.name
        when(category.id){
            1 -> holder.categoryImage.setImageResource(R.drawable.popular_movie)
            2 -> holder.categoryImage.setImageResource(R.drawable.toprated_movie)
            3 -> holder.categoryImage.setImageResource(R.drawable.upcoming_movie)
        }
//        holder.categoryImage.setImageResource(category.imageResource)
    }


    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        internal var tvCategoryId = itemView.tvCategoryId
        internal var categoryTitle  = itemView.categoryTitle
        internal var categoryImage = itemView.categoryImage

        init {
            itemView.setOnClickListener { actionClick() }
        }

        fun actionClick() {
            val intent = Intent(context?.applicationContext, FilmListActivity::class.java)
            intent.putExtra(PreferencesUtils.KeyPreferences.CATEGORY_ID, tvCategoryId.text)
            intent.putExtra(PreferencesUtils.KeyPreferences.CATEGORY_NAME, categoryTitle.text)
            context?.startActivity(intent)
        }
    }
}