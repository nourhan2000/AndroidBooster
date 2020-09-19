package com.example.myapplication.UI

import android.content.ContentValues.TAG
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceRequest.newInstance
import android.net.wifi.p2p.nsd.WifiP2pUpnpServiceRequest.newInstance
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.google.android.youtube.player.YouTubePlayerFragment.newInstance
import com.google.android.youtube.player.YouTubePlayerSupportFragment.newInstance
import kotlinx.android.synthetic.main.favourites_fragment.*
import kotlinx.android.synthetic.main.top_movies_fragment.*

import kotlinx.android.synthetic.main.popular_movies_fragment.*
import org.xmlpull.v1.XmlPullParserFactory.newInstance
import java.lang.reflect.Array.newInstance
import javax.xml.datatype.DatatypeFactory.newInstance
import javax.xml.parsers.DocumentBuilderFactory.newInstance
import javax.xml.parsers.SAXParserFactory.newInstance
import javax.xml.transform.TransformerFactory.newInstance
import javax.xml.xpath.XPathFactory.newInstance



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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.onError.observe(viewLifecycleOwner,{
            handelMovieError(it,requireActivity())
        })

        mainViewModel.favMovieLiveData
            .observe(viewLifecycleOwner, {
                bindMovieData(it,recycler_view_fav ,requireActivity())

            })

        mainViewModel.loadFavMovie()
    }




}