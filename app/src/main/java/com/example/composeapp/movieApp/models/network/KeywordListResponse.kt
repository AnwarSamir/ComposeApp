

package com.example.composeapp.movieApp.models.network

import androidx.compose.runtime.Immutable
import com.example.composeapp.movieApp.models.Keyword
import com.example.composeapp.movieApp.models.NetworkResponseModel

@Immutable
data class KeywordListResponse(
  val id: Int,
  val keywords: List<Keyword>
) : NetworkResponseModel
