

package com.example.composeapp.movieApp.ui.people

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composeapp.movieApp.extensions.paging
import com.example.composeapp.movieApp.models.entities.Person
import com.example.composeapp.movieApp.models.network.NetworkState
import com.example.composeapp.movieApp.models.network.onLoading
import com.example.composeapp.movieApp.network.Api
import com.example.composeapp.movieApp.network.compose.NetworkImage
import com.google.accompanist.insets.statusBarsPadding
import com.example.composeapp.movieApp.ui.main.MainScreenHomeTab
import com.example.composeapp.movieApp.ui.main.MainViewModel
import com.example.composeapp.movieApp.ui.theme.background800

@Composable
fun PeopleScreen(
  viewModel: MainViewModel,
  selectPerson: (MainScreenHomeTab, Long) -> Unit,
  lazyListState: LazyGridState,
  modifier: Modifier = Modifier
) {
  val networkState: NetworkState by viewModel.personLoadingState
  val people by viewModel.people

  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    state = lazyListState,
    modifier = modifier
      .statusBarsPadding()
      .background(MaterialTheme.colors.background)
  ) {

    paging(
      items = people,
      currentIndexFlow = viewModel.peoplePageStateFlow,
      threshold = 2,
      fetch = { viewModel.fetchNextPeoplePage() }
    ) {

      PersonCard(
        person = it,
        selectPerson = selectPerson
      )
    }
  }

  networkState.onLoading {
    Box(
      modifier = Modifier.fillMaxSize()
    ) {

      CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center)
      )
    }
  }
}

@Composable
private fun PersonCard(
  person: Person,
  selectPerson: (MainScreenHomeTab, Long) -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier
      .height(200.dp)
      .padding(4.dp)
      .clickable(
        onClick = { selectPerson(MainScreenHomeTab.PERSON, person.id) }
      ),
    color = background800,
    elevation = 8.dp,
    shape = RoundedCornerShape(8.dp)
  ) {

    ConstraintLayout(modifier = Modifier.padding(16.dp)) {
      val (image, title) = createRefs()

      NetworkImage(
        networkUrl = Api.getPosterPath(person.profile_path),
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .size(100.dp)
          .clip(CircleShape)
          .constrainAs(image) {
            centerHorizontallyTo(parent)
            top.linkTo(parent.top)
            bottom.linkTo(title.top)
          }
      )

      Text(
        text = person.name,
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Center,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
          .padding(8.dp)
          .constrainAs(title) {
            centerHorizontallyTo(parent)
            top.linkTo(image.bottom)
            bottom.linkTo(parent.bottom)
          }
      )
    }
  }
}
