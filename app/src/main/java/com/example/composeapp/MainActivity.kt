package com.example.composeapp

import android.app.Activity
import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.navHost.NavigationActivity
import com.example.composeapp.ui.theme.ComposeAppTheme
import com.example.composeapp.ui.theme.Green
import com.example.composeapp.ui.theme.views.Title
import com.example.composeapp.ui.theme.views.addPadding

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    InitLayouts()
                }
            }
        }
    }
}

@Composable
fun InitLayouts() {
    val activity = LocalContext.current as Activity
    Column(
        modifier =  Modifier.addPadding(R.dimen.d16)
    ) {
        Title(screenName = R.string.app_name, color = Color.Black)
        ClickableText(
            style = TextStyle(color = Green),
            modifier =  Modifier.addPadding(R.dimen.d8), text = AnnotatedString("Layouts")
        ) {
            activity.startActivity(Intent(activity, LayoutsActivity::class.java))
        }

        ClickableText(
            style = TextStyle(color = Green),
            modifier =  Modifier.addPadding(R.dimen.d8), text = AnnotatedString("Image")
        ) {
            activity.startActivity(Intent(activity, ImagesActivity::class.java))
        }

        ClickableText(
            style = TextStyle(color = Green),
            modifier =  Modifier.addPadding(R.dimen.d8), text = AnnotatedString("Navigation ")
        ) {
            activity.startActivity(Intent(activity, NavigationActivity::class.java))
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeAppTheme {
        InitLayouts()
    }
}