package com.example.myapplication.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.details_fragment.*


class DetailsFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.top_movies_fragment, container, false)
        activity?.let{
            mainViewModel=ViewModelProvider(this).get(MainViewModel::class.java)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val youtubeAPIkey = "AIzaSyAGF4s6LEPwk80wuf7v0gUG5ey8jNQS17I"
        val movieId =" "
        val intent = YouTubeStandalonePlayer.createVideoIntent(requireActivity(), youtubeAPIkey, movieId);
        vid_button.setOnClickListener{
            startActivity(intent);
        }
    }

}