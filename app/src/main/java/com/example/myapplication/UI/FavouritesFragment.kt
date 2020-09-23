package com.example.myapplication.UI


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.recycler.MovieAdapter
import kotlinx.android.synthetic.main.favourites_fragment.*


class FavouritesFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.favourites_fragment, container, false)
        activity?.let{
            mainViewModel=ViewModelProvider(this).get(MainViewModel::class.java)
        }
        return view
    }
    private lateinit var Adapter: MovieAdapter
    val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.onError.observe(viewLifecycleOwner,{
            handelMovieError(it,requireActivity())
        })

        mainViewModel.favMovieLiveData
            .observe(viewLifecycleOwner, {
                Adapter = MovieAdapter(it)
                bindMovieData(recycler_view_fav,"fav",Adapter,linearLayoutManager)
            })

        mainViewModel.loadFavMovie()
    }




}