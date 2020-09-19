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
        val youtubeAPIkey = "AIzaSyAGF4s6LEPwk80wuf7v0gUG5ey8jNQS17I"
        val movieId =" "
        val intent = YouTubeStandalonePlayer.createVideoIntent(requireActivity(), youtubeAPIkey, movieId);
        vid_button.setOnClickListener{
            startActivity(intent);
        }
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

}