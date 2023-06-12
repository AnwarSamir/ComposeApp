package com.example.composeapp.movieApp.models.topRated

data class TopRatedResponse(
    val page: Int?=null,
    val results: List<Result> = emptyList(),
    val total_pages: Int?=null,
    val total_results: Int?=null
)