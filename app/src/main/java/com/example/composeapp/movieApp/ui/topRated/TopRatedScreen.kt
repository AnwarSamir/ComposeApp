package com.example.composeapp.movieApp.ui.topRated

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composeapp.movieApp.extensions.paging
import com.example.composeapp.movieApp.extensions.pagingList
import com.example.composeapp.movieApp.models.topRated.Result
import com.example.composeapp.movieApp.network.Api
import com.example.composeapp.movieApp.network.compose.NetworkImage
import com.google.accompanist.insets.statusBarsPadding
import java.lang.Math.ceil
import java.lang.Math.floor

@Composable
fun TopRatedScreen(viewModel: TopRatedViewModel, modifier: Modifier) {
    val topRated by viewModel.topRated
    val loading by viewModel.loadingState

    Column(modifier = modifier.statusBarsPadding()) {
        if (loading)
            LinearProgressIndicator()

        LazyColumn {
            pagingList(
                items = topRated.results,
                threshold = 2,
                currentIndexFlow = viewModel.topRatedPageStateFlow,
                fetch = {
                    viewModel.fetchNextPage() }
            ) {
                TopRatedItem(result = it)
            }
        }
    }

}

@Composable
fun TopRatedItem(result: Result) {
    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
        ConstraintLayout(modifier = Modifier.padding(4.dp)) {
            val (topRatedImage, title, rating, ratingAmount, overViewText, divider) = createRefs()
            NetworkImage(
                networkUrl = Api.getPosterPath(result.poster_path),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(topRatedImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(150.dp))

            Text(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                text = result.title,
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(topRatedImage.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(bottom = 4.dp, top = 2.dp))
            Row(modifier = Modifier.constrainAs(rating) {
                top.linkTo(title.bottom)
                start.linkTo(parent.start)
            }) {
                RatingBar(rating = result.vote_average / 2, starsColor = Color.Blue)
            }
            Text(
                fontWeight = FontWeight.Bold,
                text = "From ${result.vote_count}",
                modifier = Modifier.constrainAs(ratingAmount) {
                    start.linkTo(rating.end)
                    top.linkTo(rating.top)
                    bottom.linkTo(rating.bottom)
                })
            Divider(modifier = Modifier
                .constrainAs(divider) {
                    start.linkTo(parent.start)
                    top.linkTo(rating.bottom)
                }
                .padding(top = 4.dp, bottom = 4.dp), thickness = 1.dp, color = Color.Gray)
            Text(text = result.overview, modifier = Modifier
                .constrainAs(overViewText) {
                    start.linkTo(parent.start)
                    top.linkTo(divider.bottom)
                }
                .padding(top = 4.dp))


        }
    }

}


@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }
        if (halfStar) {
            Icon(
                imageVector = Icons.Outlined.StarHalf,
                contentDescription = null,
                tint = starsColor
            )
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}