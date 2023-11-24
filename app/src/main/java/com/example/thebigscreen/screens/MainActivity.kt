package com.example.thebigscreen.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.thebigscreen.NavGraphs
import com.example.thebigscreen.ui.theme.TheBigScreenTheme
import com.ramcosta.composedestinations.DestinationsNavHost
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
