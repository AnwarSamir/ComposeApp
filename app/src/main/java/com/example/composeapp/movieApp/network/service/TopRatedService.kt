package com.example.composeapp.movieApp.network.service

import com.example.composeapp.movieApp.models.network.VideoListResponse
import com.example.composeapp.movieApp.models.topRated.TopRatedResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TopRatedService {
    @GET("/3/movie/top_rated")
    suspend fun fetchTopRated(@Query("page") page: Int): TopRatedResponse
}