package com.example.composeapp.navHost

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun WelcomeScreen(navController: NavController) {
    Column {
        Text(text = "Welcome!")
        Button(onClick = { navController.navigate("secondScreen") }) {
            Text(text = "Continue")
        }
    }
}