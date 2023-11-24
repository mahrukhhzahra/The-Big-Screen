package com.example.thebigscreen.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
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
//                DestinationsNavHost(navGraph = NavGraphs.root)
                DestinationsNavHost(navGraph = NavGraphs.root)

            }
        }

    }

}


@Composable
@Destination
fun AuthCheckScreen(navigator: DestinationsNavigator) {
    // Check authentication status using Firebase Auth
    val auth = FirebaseAuth.getInstance()
    if (auth.currentUser != null) {
        // User is signed in, navigate to the home screen
        navigator.navigate(HomeDestination())
    } else {
        // User is not signed in, show authentication screen
        navigator.navigate(SignUpScreenDestination())
    }
}