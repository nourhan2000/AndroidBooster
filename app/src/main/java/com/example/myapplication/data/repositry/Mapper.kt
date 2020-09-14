package com.example.myapplication.data.repositry

import com.example.myapplication.data.database.Movies.Movie
import com.example.myapplication.data.database.Reviews.Review
import com.example.myapplication.data.database.Videos.Video
import com.example.myapplication.data.modules.MovieResponse
import com.example.myapplication.data.modules.ReviewResponse
import com.example.myapplication.data.modules.VideoResponse

class Mapper {
     fun convertToMovie(movieResponse: MovieResponse): List<Movie>{
        val movies = mutableListOf<Movie>()
        movieResponse.MoviesList.forEach{
            movies.add(Movie(it.movieId,it.PosterPath,it.OriginalTitle,it.originalLanguage,it.voteAverage,it.overview,it.releaseDate))
        }
        return movies
    }

     fun convertToVideo(videoResponse: VideoResponse): List<Video>{
        val videos = mutableListOf<Video>()
        videoResponse.vidsList.forEach{
            videos.add(Video(it.vidId,it.vidKey,it.name,it.site,it.type))
        }
        return videos
    }

     fun convertToReview(reviewResponse: ReviewResponse): List<Review>{
        val reviews = mutableListOf<Review>()
        reviewResponse.reviewResult.forEach{
            reviews.add(Review(it.movieContent))
        }
        return reviews
    }
}