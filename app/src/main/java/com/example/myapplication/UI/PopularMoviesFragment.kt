package com.example.myapplication.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.recycler.MovieAdapter
import kotlinx.android.synthetic.main.popular_movies_fragment.*


class PopularMoviesFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.popular_movies_fragment, container, false)
        activity?.let{
            mainViewModel=ViewModelProvider(this).get(MainViewModel::class.java)
        }
        return view
    }

    private lateinit var Adapter: MovieAdapter
    var page = 1
    var isPagination = false
    val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.onError.observe(viewLifecycleOwner, {
            handelMovieError(it,requireActivity())
        })
        mainViewModel.movieLiveData
            .observe(viewLifecycleOwner, {
                Adapter = MovieAdapter(it)
                bindMovieData(recycler_view_pop,"pop",Adapter,linearLayoutManager)
                pagination(it,isPagination,linearLayoutManager,Adapter,recycler_view_pop)
            })

        mainViewModel.loadMovieData(myPage = page)

        recycler_view_pop.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    page++
                    isPagination = true
                    mainViewModel.loadMovieData(myPage = page)

                }
            }
        })

        }

    }


