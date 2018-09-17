package com.devjcastro.androidtestmovies.features.movie.detail.sections

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.devjcastro.androidtestmovies.R
import com.devjcastro.androidtestmovies.data.dtos.YoutubeVideoDTO
import com.devjcastro.androidtestmovies.features.adapters.YoutubeVideoAdapter
import com.devjcastro.androidtestmovies.features.movie.detail.DetailFilmActivity
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_list_movies.*
import kotlinx.android.synthetic.main.fragment_list_movies.view.*


class ListMoviesFragment : Fragment() {


    var subscription: Disposable? = null
    private var listener: OnFragmentInteractionListener? = null
    lateinit var containerView: View
    lateinit var adapter: YoutubeVideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        containerView = inflater.inflate(R.layout.fragment_list_movies, container, false)
        containerView.rvVideos.apply {
            layoutManager = LinearLayoutManager(activity)
        }
        return containerView
    }

    fun initSubscription(){
        subscription = DetailFilmActivity.subject?.subscribe(this::loadVideos)
    }

    override fun onResume() {
        super.onResume()
        initSubscription()
    }

    override fun onPause() {
        super.onPause()
        subscription?.dispose()
    }


    private fun loadVideos(videos: List<YoutubeVideoDTO>?){

        if(videos != null && videos.isNotEmpty()){
            containerView.ivEmpty.visibility = View.GONE
            containerView.progressBar.visibility = View.GONE

            adapter = YoutubeVideoAdapter(videos)
            rvVideos.adapter = adapter
            containerView.rvVideos.visibility = View.VISIBLE
        }
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
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
         * @return A new instance of fragment ListMoviesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ListMoviesFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

}
