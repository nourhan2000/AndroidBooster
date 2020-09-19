package com.example.myapplication.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import kotlinx.android.synthetic.main.top_movies_fragment.*


class TopMoviesFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel=activity?.run{
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainViewModel.onError.observe(viewLifecycleOwner,{
            handelMovieError(it,requireContext())
        })

        mainViewModel.topMovieLiveData
            .observe(viewLifecycleOwner, {
                bindMovieData(it,recycler_view_top ,requireActivity())

            })

        mainViewModel.loadMovieData()

        return inflater.inflate(R.layout.top_movies_fragment, container, false)
    }

}