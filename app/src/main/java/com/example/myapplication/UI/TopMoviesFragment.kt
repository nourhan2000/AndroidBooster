package com.example.myapplication.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.recycler.MovieAdapter
import kotlinx.android.synthetic.main.horizental_item.*
import kotlinx.android.synthetic.main.top_movies_fragment.*


class TopMoviesFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.top_movies_fragment, container, false)
         activity?.let{
             mainViewModel=ViewModelProvider(this).get(MainViewModel::class.java)
        }
        return view }

    private lateinit var Adapter: MovieAdapter
    var page = 1
    var isPagination = false
    val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.onError.observe(viewLifecycleOwner,{
            handelMovieError(it,requireActivity())
        })

        mainViewModel.topMovieLiveData
            .observe(viewLifecycleOwner, {
                bindMovieData(it,recycler_view_top ,requireActivity(),"top")

            })

        mainViewModel.loadTopMovieData(myPage = page)


    }

}