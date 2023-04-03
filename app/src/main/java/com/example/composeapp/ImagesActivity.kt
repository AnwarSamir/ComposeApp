package com.example.composeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import com.example.composeapp.ui.theme.ComposeAppTheme
import com.example.composeapp.ui.theme.Green
import com.example.composeapp.ui.theme.views.Title
import com.example.composeapp.ui.theme.views.addPadding

class ImagesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Layouts()
                    // for image next time
                    // https://developer.android.com/jetpack/compose/graphics/images/customize
                }
            }
        }
    }

    @Composable
    private fun Layouts() {
        Column(Modifier.addPadding(R.dimen.d16)) {
            Title(screenName = R.string.Images_title, color = Green)
            Text(text = "Circle Image")
            Image(
                painter = painterResource(id = R.drawable.poster),
                contentDescription = stringResource(id = R.string.layout_column),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )

            Text(text = "Rounded Corner Image")
            Image(
                painter = painterResource(id = R.drawable.poster),
                contentDescription = stringResource(id = R.string.layout_column),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Text(text = "Rounded Corner Image with border")
            Image(
                painter = painterResource(id = R.drawable.poster),
                contentDescription = stringResource(id = R.string.layout_column),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .border(BorderStroke(2.dp, Color.Red),RoundedCornerShape(8.dp))
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Text(text = "Image with custom aspect ratio")
            Image(
                painter = painterResource(id = R.drawable.poster),
                contentDescription = stringResource(id = R.string.layout_column),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(16f/9f)
                    .border(BorderStroke(2.dp, Color.Red),RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}