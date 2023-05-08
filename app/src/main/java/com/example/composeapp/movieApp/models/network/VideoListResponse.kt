

package com.example.composeapp.movieApp.models.network

import androidx.compose.runtime.Immutable
import com.example.composeapp.movieApp.models.NetworkResponseModel
import com.example.composeapp.movieApp.models.Video

@Immutable
data class VideoListResponse(
  val id: Int,
  val results: List<Video>
) : NetworkResponseModel
