package com.example.myapplication.UI

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.recycler.ReviewAdapter
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_fragment.*


class DetailsFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var intent:Intent
    private var vidKey:String=" "

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.details_fragment, container, false)
        activity?.let{
            mainViewModel=ViewModelProvider(this).get(MainViewModel::class.java)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val youtubeAPIkey = "AIzaSyAGF4s6LEPwk80wuf7v0gUG5ey8jNQS17I"
        var movieId =this.requireArguments().getLong("movie_id")

        textView11.text= this.requireArguments().getString("vote")
        textView10.text= this.requireArguments().getString("date")
        textView9.text = this.requireArguments().getString("overView")
        var photo = this.requireArguments().getString("photo")
        Picasso.get().load(photo).into(imageView2)

        mainViewModel.onError.observe(viewLifecycleOwner,{
            handelMovieError(it,requireActivity())
        })

        mainViewModel.reviewLiveData.observe(viewLifecycleOwner,{
            recycler_view_rev.hasFixedSize()
            recycler_view_rev.layoutManager = LinearLayoutManager(requireContext())
            recycler_view_rev.adapter = ReviewAdapter(it)
        })
        mainViewModel.loadMovieReviews(movieId)



        mainViewModel.videoLiveData.observe(viewLifecycleOwner,{
            if(it.vidKey!=null)
                vidKey=it.vidKey
        })
        mainViewModel.loadMovieVideo(movieId)

        vid_button.setOnClickListener{
            intent = YouTubeStandalonePlayer.createVideoIntent(requireActivity(), youtubeAPIkey, vidKey)
            startActivity(intent)
        }

    }

}