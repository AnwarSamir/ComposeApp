package com.example.composeapp.navHost

import android.os.Bundle
import android.widget.Toolbar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.ui.theme.AppTopBar
import com.example.composeapp.utils.SECOND_SCREEN
import com.example.composeapp.utils.WELCOME

class NavigationActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
           Scaffold( topBar = { AppTopBar(onUpClick = {navController.navigateUp()})}) { padding->
               Box(Modifier.padding(padding)) {
                   NavHost(navController, startDestination = WELCOME) {
                       composable(WELCOME) { WelcomeScreen(navController) }
                       composable(SECOND_SCREEN) { SecondScreen() }
                   }
               }
           }

        }
    }




}