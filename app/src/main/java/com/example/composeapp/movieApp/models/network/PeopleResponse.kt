

package com.example.composeapp.movieApp.models.network

import androidx.compose.runtime.Immutable
import com.example.composeapp.movieApp.models.NetworkResponseModel
import com.example.composeapp.movieApp.models.entities.Person

@Immutable
data class PeopleResponse(
    val page: Int,
    val results: List<Person>,
    val total_results: Int,
    val total_pages: Int
) : NetworkResponseModel
