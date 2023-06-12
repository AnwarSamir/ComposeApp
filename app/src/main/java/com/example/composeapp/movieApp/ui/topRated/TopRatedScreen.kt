package com.example.composeapp.movieApp.ui.topRated

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composeapp.movieApp.models.topRated.Result
import com.example.composeapp.movieApp.network.Api
import com.example.composeapp.movieApp.network.compose.NetworkImage
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun TopRatedScreen(viewModel: TopRatedViewModel, modifier: Modifier) {
    val topRated by viewModel.topRated
    val loading by viewModel.loadingState

    Column(modifier = modifier.statusBarsPadding()) {
        if (loading)
        LinearProgressIndicator()
        LazyColumn {
            item {
                topRated.results.forEach {
                    topRatedItem(it)
                }
            }
        }
    }

}

@Composable
fun topRatedItem(result: Result) {
    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(bottom = 4.dp)) {
        ConstraintLayout(modifier = Modifier.padding(4.dp)) {
            val (topRatedImage,title) = createRefs()
            NetworkImage(
                networkUrl = Api.getPosterPath(result.poster_path),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(topRatedImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(100.dp))

            Text(text = result.title, modifier = Modifier.constrainAs(title){
                top.linkTo(topRatedImage.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
    }
}

}