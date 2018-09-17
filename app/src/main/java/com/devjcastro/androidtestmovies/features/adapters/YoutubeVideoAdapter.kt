package com.devjcastro.androidtestmovies.features.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devjcastro.androidtestmovies.R
import android.view.animation.AnimationUtils
import com.devjcastro.androidtestmovies.data.dtos.YoutubeVideoDTO
import com.devjcastro.androidtestmovies.features.movie.detail.sections.YoutubeVideoActivity
import kotlinx.android.synthetic.main.item_youtube_video_list.view.*


class YoutubeVideoAdapter(private val data: List<YoutubeVideoDTO>) : RecyclerView.Adapter<YoutubeVideoAdapter.FilmViewHolder>() {

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

        val view = inflater!!.inflate(R.layout.item_youtube_video_list, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = data[position]
        holder.tvVideoId.text = film.key
        holder.tvTitle.text = film.name

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

        internal var tvVideoId = itemView.tvVideoId
        internal var tvTitle = itemView.tvTitle

        init {
            itemView.setOnClickListener { actionClick() }
        }

        fun actionClick() {
            var intent = Intent(context, YoutubeVideoActivity::class.java)
            intent.putExtra("videoID", tvVideoId.text.toString())
            context?.startActivity(intent)
        }
    }
}