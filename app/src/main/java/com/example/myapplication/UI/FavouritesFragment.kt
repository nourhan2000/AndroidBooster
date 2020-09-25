package com.example.myapplication.UI


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.favourites_fragment.*


class FavouritesFragment : Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var favMovies=FavoriteObject.getFav()

        if (favMovies.isNotEmpty()){
            bindMovieData(requireActivity(),favMovies,recycler_view_fav,"fav")
        }
        else{
            handelMovieError("no fav Movies",requireActivity())
        }
//        mainViewModel.onError.observe(viewLifecycleOwner,{
//            handelMovieError(it,requireActivity())
//        })

//        mainViewModel.favMovieLiveData
//            .observe(viewLifecycleOwner, {
//                bindMovieData(requireActivity(),it,recycler_view_fav,"fav")
//            })
//
//        mainViewModel.loadFavMovie()
    }




}