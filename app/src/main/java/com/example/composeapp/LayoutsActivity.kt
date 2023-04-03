package com.example.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.ui.theme.ComposeAppTheme
import com.example.composeapp.ui.theme.Green
import com.example.composeapp.ui.theme.transBlack
import com.example.composeapp.ui.theme.views.Title
import com.example.composeapp.ui.theme.views.addPadding

class LayoutsActivity : ComponentActivity() {
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
                }
            }
        }
    }

    @Composable
    fun Layouts() {
        Column(modifier = Modifier.addPadding(R.dimen.d16)) {
            Title(screenName = R.string.layout_title, color = Green)
            infoText()
            ColumnEx()
            RowEx()
            LazyColumEx()
            LazyRowEx()
        }

    }

    @Composable
    private fun infoText()
    {
        val uriHandler = LocalUriHandler.current
        Column {
            Text(text = "Jetpack Compose makes it easy to design an efficient layout for your app.for More ")
            ClickableText(style = TextStyle(color = Color.Blue), text = AnnotatedString("Click Here->",), onClick = {
                uriHandler.openUri("https://developer.android.com/jetpack/compose/layouts")
            })
        }

    }



    @Composable
    private fun ColumnEx(){
        Text(
            text = stringResource(R.string.layout_column),
            color = Color.Black,
        )
        Column {
            Text(text = "Yep -->")
            Text(text = "Yep -->")
            Text(text = "Yep -->")
        }
    }
    
    @Composable
    private fun RowEx(){
        Box(modifier = Modifier.padding(top = dimensionResource(R.dimen.d16)))
        Text(
            text = stringResource(R.string.layout_row),
            color = Color.Black,
        )
        Row {
            Text(text = "Yep -->")
            Text(text = "Yep -->")
            Text(text = "Yep -->")
        }
    }

    @Composable
    private fun LazyColumEx(){
        Box(modifier = Modifier.padding(top = dimensionResource(R.dimen.d16)))
        Text(
            text = stringResource(R.string.layout_lazy_column),
            color = Color.Black,
        )
        LazyColumn {
           item {
               for (i in 1..10){
                   Text(text = "Item $i")
               }
           }
        }
    }


    @Composable
    private fun LazyRowEx(){
        Box(modifier = Modifier.padding(top = dimensionResource(R.dimen.d16)))
        Text(
            text = stringResource(R.string.layout_lazy_row),
            color = Color.Black,
        )
        LazyRow {
            item {
                for (i in 1..10){
                    ListItem()
                }
            }
        }
    }

    @Preview
    @Composable
    private fun ListItem(){
        Box(modifier = Modifier.padding(4.dp).size(width = 135.dp, height = 190.dp), contentAlignment = Alignment.BottomStart){
            Image(painter = painterResource(id = R.drawable.poster), contentDescription = "posterItem")
            Box(modifier = Modifier.background(color = transBlack).fillMaxSize())
            Text(text = "Name will be Here" , textAlign = TextAlign.Center, modifier = Modifier.addPadding(R.dimen.d4), fontSize = 12.sp)
        }
    }

}