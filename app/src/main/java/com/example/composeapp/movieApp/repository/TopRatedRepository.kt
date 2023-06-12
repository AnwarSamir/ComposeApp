

package com.example.composeapp.movieApp.repository

import com.example.base.BaseRepository
import com.example.composeapp.movieApp.network.service.TopRatedService

import timber.log.Timber

class TopRatedRepository constructor(
    private val peopleService: TopRatedService) : BaseRepository() {

  init {
    Timber.d("Injection PeopleRepository")
  }


  fun getTopRated() = safeApiCalls {
    return@safeApiCalls peopleService.fetchTopRated()
  }

}
