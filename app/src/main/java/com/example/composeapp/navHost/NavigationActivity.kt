package com.example.composeapp.navHost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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
           Scaffold( topBar = { AppTopBar(onUpClick = {
               if (navController.navigateUp())
                   navController.navigateUp()
               else onBackPressedDispatcher.onBackPressed()
           })}) { padding->
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