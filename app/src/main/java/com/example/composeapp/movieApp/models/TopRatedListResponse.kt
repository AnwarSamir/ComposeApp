

package com.example.composeapp.movieApp.models

import androidx.compose.runtime.Immutable
import com.example.composeapp.movieApp.models.NetworkResponseModel
import com.example.composeapp.movieApp.models.Video

@Immutable
data class TopRatedListResponse(
  val id: Int,
  val results: List<Video>
) : NetworkResponseModel
