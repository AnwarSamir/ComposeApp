

package com.example.composeapp.movieApp.models.network

import androidx.compose.runtime.Immutable
import com.example.composeapp.movieApp.models.NetworkResponseModel

@Immutable
data class PersonDetail(
  val birthday: String?,
  val known_for_department: String,
  val place_of_birth: String?,
  val also_known_as: List<String>?,
  val biography: String
) : NetworkResponseModel
