package com.devjcastro.androidtestmovies.features.adapters

import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devjcastro.androidtestmovies.R
import com.devjcastro.androidtestmovies.data.dtos.FilmDTO
import com.devjcastro.androidtestmovies.utils.extensions.loadUrl
import kotlinx.android.synthetic.main.item_film_list.view.*
import android.view.animation.AnimationUtils
import com.devjcastro.androidtestmovies.features.movie.detail.DetailFilmActivity
import com.devjcastro.androidtestmovies.utils.PreferencesUtils


class FilmAdapter(private val data: List<FilmDTO>) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    private var lastPosition = -1
    private var context: Context? = null
    private var inflater: LayoutInflater? = null


    interface DetailFilmListener {
        fun launchActivityForDetail(filmId: Long)
    }

    lateinit var listener: DetailFilmListener

    fun setCallbackListener(listener: DetailFilmListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        context = parent.context
        inflater = LayoutInflater.from(context)

        val view = inflater!!.inflate(R.layout.item_film_list, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = data[position]
        holder.tvFilmId.text = film.id.toString()
        holder.tvTitle.text = film.title
        holder.tvOverview.text = film.overview
        val url = "https://image.tmdb.org/t/p/w500" + film.posterPath
        holder.imgCover.loadUrl(url)

        film.voteAverage?.let {
            val rating = it * 10 / 20
            holder.ratingBar.rating = rating.toFloat()
            holder.ratingBarText.text = rating.toString()
        }
        setAnimation(holder.itemView, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    inner class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tvFilmId = itemView.tvFilmId
        internal var imgCover = itemView.imgCover
        internal var tvTitle = itemView.tvTitle
        internal var tvOverview = itemView.tvOverview
        internal var ratingBar = itemView.ratingBar
        internal var ratingBarText = itemView.ratingBarText

        init {
            itemView.setOnClickListener { actionClick() }
        }

        fun actionClick() {
            listener.launchActivityForDetail(tvFilmId.text.toString().toLong())
        }
    }
}