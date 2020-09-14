package com.example.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.data.PageListAdapter
import com.example.myapplication.data.database.Movie
import com.example.myapplication.data.network.APIclient
import com.example.myapplication.data.network.APIinterface
import com.example.myapplication.recycler.MovieAdapter
import com.oxcoding.moviemvvm.data.repository.NetworkState
import retrofit2.Retrofit

class MainActivity : AppCompatActivity(){

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.onError.observe(this, {
            handelMovieError(it)
        })

        mainViewModel.movieLiveData
            .observe(this, {
                bindMovieData(it)
            })

        mainViewModel.loadMovieData()

    }

    private fun bindMovieData(movie: List<Movie>)
    {
        recycler_view.hasFixedSize()
        recycler_view.layoutManager = GridLayoutManager(this@MainActivity ,2)
        recycler_view.adapter = MovieAdapter( movie )
    }

    private fun handelMovieError(errorMsg: String)
    {
        Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_LONG).show()
    }

    private lateinit var viewModel: MainViewModel

    lateinit var movieRepository: MoviePagedListRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService : Retrofit = APIclient.getClient()

        movieRepository = MoviePagedListRepository(apiService)

        viewModel = getViewModel()

        val movieAdapter = PopularMoviePagedListAdapter(this)

        val gridLayoutManager = GridLayoutManager(this, 3)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = movieAdapter.getItemViewType(position)
                if (viewType == movieAdapter.MOVIE_VIEW_TYPE) return  1    // Movie_VIEW_TYPE will occupy 1 out of 3 span
                else return 3                                              // NETWORK_VIEW_TYPE will occupy all 3 span
            }
        };


        rv_movie_list.layoutManager = gridLayoutManager
        rv_movie_list.setHasFixedSize(true)
        rv_movie_list.adapter = movieAdapter

        viewModel.moviePagedList.observe(this, Observer {
            movieAdapter.submitList(it)
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar_popular.visibility = if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error_popular.visibility = if (viewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE

            if (!viewModel.listIsEmpty()) {
                movieAdapter.setNetworkState(it)
            }
        })


    
}