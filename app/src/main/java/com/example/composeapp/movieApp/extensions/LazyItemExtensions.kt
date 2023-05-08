
package com.example.composeapp.movieApp.extensions

import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import com.example.composeapp.movieApp.network.Api
import kotlinx.coroutines.flow.StateFlow

inline fun <T> LazyGridScope.paging(
  items: List<T>,
  currentIndexFlow: StateFlow<Int>,
  threshold: Int = 4,
  pageSize: Int = Api.PAGING_SIZE,
  crossinline fetch: () -> Unit,
  crossinline itemContent: @Composable LazyGridItemScope.(item: T) -> Unit,
) {
  val currentIndex = currentIndexFlow.value

  itemsIndexed(items) { index, item ->

    itemContent(item)

    if ((index + threshold + 1) >= pageSize * (currentIndex - 1)) {
      fetch()
    }
  }
}
