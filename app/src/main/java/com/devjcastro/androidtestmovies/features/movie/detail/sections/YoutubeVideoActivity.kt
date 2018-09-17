package com.devjcastro.androidtestmovies.features.movie.detail.sections

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.devjcastro.androidtestmovies.R
import com.devjcastro.androidtestmovies.utils.PreferencesUtils
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_detail_film.*
import kotlinx.android.synthetic.main.activity_youtube_video.*

class YoutubeVideoActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_video)

        init()
    }

    fun init(){
        viewYoutubePlayer.initialize("AIzaSyCfKmXFoBeyfgz7PCginMtBE_LzoUASmyU", this)
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, player: YouTubePlayer?, p2: Boolean) {

        val bundle = intent.extras
        if (bundle != null) {
            val videoID = bundle.getString("videoID")
            //player?.loadVideo("6ZfuNTqbHE8")
            player?.loadVideo(videoID)
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
