package com.example.thebigscreen.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.example.thebigscreen.NavGraphs
import com.example.thebigscreen.destinations.HomeDestination
import com.example.thebigscreen.destinations.SignUpScreenDestination
import com.example.thebigscreen.ui.theme.TheBigScreenTheme
import com.google.firebase.auth.FirebaseAuth
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheBigScreenTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}


/*
@Composable
fun CheckUserLoginStatus(navigator: DestinationsNavigator) {
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    LaunchedEffect(key1 = Unit) {
        if (currentUser != null) {
            // User is logged in, navigate to home screen
            navigator.navigate(HomeDestination())
        } else {
            // User is not logged in, show login screen
            navigator.navigate(SignUpScreenDestination())
        }
    }
}*/
