

package com.example.composeapp.movieApp.repository

import androidx.annotation.WorkerThread
import com.example.composeapp.movieApp.models.Keyword
import com.example.composeapp.movieApp.models.Review
import com.example.composeapp.movieApp.models.Video
import com.example.composeapp.movieApp.network.service.MovieService
import com.example.composeapp.movieApp.persistence.MovieDao
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class MovieRepository constructor(
    private val movieService: MovieService,
    private val movieDao: MovieDao
) : Repository {

  init {
    Timber.d("Injection MovieRepository")
  }

  @WorkerThread
  fun loadKeywordList(id: Long) = flow<List<Keyword>> {
    val movie = movieDao.getMovie(id)
    var keywords = movie.keywords
    if (keywords.isNullOrEmpty()) {
      val response = movieService.fetchKeywords(id)
      response.suspendOnSuccess {
        keywords = data.keywords
        movie.keywords = keywords
        emit(keywords ?: listOf())
        movieDao.updateMovie(movie)
      }
    } else {
      emit(keywords ?: listOf())
    }
  }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun loadVideoList(id: Long) = flow<List<Video>> {
    val movie = movieDao.getMovie(id)
    var videos = movie.videos
    if (videos.isNullOrEmpty()) {
      movieService.fetchVideos(id)
        .suspendOnSuccess {
          videos = data.results
          movie.videos = videos
          movieDao.updateMovie(movie)
          emit(videos ?: listOf())
        }
    } else {
      emit(videos ?: listOf())
    }
  }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun loadReviewsList(id: Long) = flow<List<Review>> {
    val movie = movieDao.getMovie(id)
    var reviews = movie.reviews
    if (reviews.isNullOrEmpty()) {
      movieService.fetchReviews(id)
        .suspendOnSuccess {
          reviews = data.results
          movie.reviews = reviews
          movieDao.updateMovie(movie)
          emit(reviews ?: listOf())
        }
    } else {
      emit(reviews ?: listOf())
    }
  }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun loadMovieById(id: Long) = flow {
    val movie = movieDao.getMovie(id)
    emit(movie)
  }.flowOn(Dispatchers.IO)
}
