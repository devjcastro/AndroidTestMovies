package com.devjcastro.androidtestmovies.features.movie.detail.sections

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.devjcastro.androidtestmovies.R
import com.devjcastro.androidtestmovies.data.dtos.FilmDTO
import com.devjcastro.androidtestmovies.data.dtos.ResponseVideosDTO
import com.devjcastro.androidtestmovies.di.detailfilm.DaggerDetailFilmComponent
import com.devjcastro.androidtestmovies.di.detailfilm.DetailFilmModule
import com.devjcastro.androidtestmovies.features.movie.detail.DetailFilmActivity
import com.devjcastro.androidtestmovies.features.movie.detail.IDetailFilmPresenter
import com.devjcastro.androidtestmovies.features.movie.detail.IDetailFilmView
import com.devjcastro.androidtestmovies.utils.extensions.loadUrl
import kotlinx.android.synthetic.main.fragment_detail.view.*
import javax.inject.Inject

class DetailFragment : Fragment(), IDetailFilmView {

    @Inject
    lateinit var presenter: IDetailFilmPresenter
    private var listener: OnFragmentInteractionListener? = null
    var filmId: Long = 0

    var containerView: View? = null
    var videos: ResponseVideosDTO? = null


    init {
        DaggerDetailFilmComponent.builder()
                .detailFilmModule(DetailFilmModule())
                .build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        containerView = inflater.inflate(R.layout.fragment_detail, container, false)

        initData()

        return containerView
    }


    fun initData(){
        presenter.attach(this@DetailFragment)
        presenter.getDetailMovie(filmId)
        presenter.getAllVideos(filmId)

        containerView?.tvViewTrailer?.setOnClickListener { viewTrailer() }
    }


    fun viewTrailer(){
        var trailer = videos?.results?.filter { it.type == "Trailer" }?.first()
        trailer?.let {
            var intent = Intent(activity, YoutubeVideoActivity::class.java)
            intent.putExtra("videoID", it.key)
            startActivity(intent)
        }
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun showDetailFilm(film: FilmDTO?) {
        film?.posterPath?.let {
            val url = "https://image.tmdb.org/t/p/w500" + it
            containerView?.imgCover?.loadUrl(url)
        }

        film?.voteAverage?.let {
            val rating = it * 10 / 20
            containerView?.ratingBar?.rating = rating.toFloat()
            containerView?.ratingBarText?.text = rating.toString()
        }

        containerView?.tvTitle?.text = film?.title
        containerView?.tvOverview?.text = film?.overview
        containerView?.tvHomepage?.text = film?.homePage
    }

    override fun getAllVideos(videos: ResponseVideosDTO?) {
        videos?.let {
            this.videos = it
            it.results?.let {
                DetailFilmActivity.subject?.onNext(it)
            }
        }
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                DetailFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
