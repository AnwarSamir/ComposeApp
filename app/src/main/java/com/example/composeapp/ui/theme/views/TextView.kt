package com.example.composeapp.ui.theme.views

import androidx.annotation.DimenRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.R
import org.w3c.dom.Text

@Composable
fun CustomTextView(
    text: String,
    color: Color,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text,
        style = TextStyle(
            color = color,
            fontSize = fontSize,
            fontWeight = fontWeight
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}
@Composable
fun  Modifier.addPadding(@DimenRes value:Int) = this.padding(dimensionResource(value))


@Composable
fun Title(screenName:Int,color: Color)
{
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(screenName),
            color = color,
            fontSize = 18.sp
        )
    }
}