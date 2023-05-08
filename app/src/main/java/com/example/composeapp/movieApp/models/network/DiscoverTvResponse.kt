

package com.example.composeapp.movieApp.models.network

import androidx.compose.runtime.Immutable
import com.example.composeapp.movieApp.models.NetworkResponseModel
import com.example.composeapp.movieApp.models.entities.Tv

@Immutable
data class DiscoverTvResponse(
    val page: Int,
    val results: List<Tv>,
    val total_results: Int,
    val total_pages: Int
) : NetworkResponseModel
