

package com.example.composeapp.movieApp.network.compose

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.composeapp.R

class NetworkUrlPreviewProvider : PreviewParameterProvider<Int> {
  override val count: Int
    get() = super.count
  override val values: Sequence<Int>
    get() = sequenceOf(R.drawable.icon_youtube)
}
