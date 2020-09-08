package com.example.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.data.database.Movie
import com.example.myapplication.recycler.MovieAdapter
import com.example.myapplication.data.repositry.MovieRepository
import com.example.myapplication.data.repositry.MovieRepository.requestMovies
import androidx.activity.viewModels


class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.movieLiveData
            .observe(this, {

                bindMovieData(it)
            })

        mainViewModel.onError.observe(this, {
            handelWeatherError(it)
        })

        mainViewModel.loadWeatherData()

        refresh_btn.setOnClickListener {
            if (location_et.text.isNotEmpty())
                mainViewModel.loadWeatherData(location_et.text.toString())
        }

        location_et.addTextChangedListener {
            val text = it.toString()
            if (text.isNotEmpty())
                refresh_btn.setImageResource(R.drawable.ic_search_24)
            else
                refresh_btn.setImageResource(R.drawable.ic_refresh_white_24)
        }
    }

    override fun onMoviesAvailable(movies: List<Movie>) {
        recycler_view.hasFixedSize()
        recycler_view.layoutManager = GridLayoutManager(this@MainActivity ,2)
        recycler_view.adapter = MovieAdapter(movies)

    }

    override fun onMoviesUnavailable(msg: String) {
        Toast.makeText(this@MainActivity ,msg,Toast.LENGTH_SHORT).show()
    }


    private fun bindMovieData(weather: WeatherResponse) {
        location_et.text = null
        weather_text_unit.text = "C"
        weather_text_value.text = weather.weatherData.temp.toString()
        weather_city_name.text = weather.cityName
        if (weather.weatherList.isNotEmpty())
            weather_status.text = weather.weatherList.first().status
    }

    private fun handelWeatherError(errorMsg: String) {
        weather_loading.hide()
        refresh_btn.isEnabled = true
        Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_LONG).show()
    }



}