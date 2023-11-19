package com.example.thebigscreen.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.thebigscreen.R
import com.example.thebigscreen.screens.destinations.HomeDestination
import com.example.thebigscreen.sharedComposables.LottieLoader
import com.example.thebigscreen.ui.theme.AppPrimaryColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

//@RootNavGraph(start = true)
@Destination(start = true)
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator?
) {
    var animateLogo by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(AppPrimaryColor)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {

            LottieLoader(
                modifier = Modifier.size(270.dp),
                lottieFile = R.raw.bubble
            )

            LaunchedEffect(Unit) {
                delay(2000)
                animateLogo = true
                delay(2000)
                navigator!!.popBackStack()
                navigator.navigate(HomeDestination())
            }

            this@Column.AnimatedVisibility(
                visible = animateLogo.not(),
                exit = fadeOut(
                    animationSpec = tween(durationMillis = 2000)
                ) + scaleOut(animationSpec = tween(durationMillis = 2000)),
            ) {
                Image(
                    modifier = Modifier
                        .widthIn(max = 170.dp)
                        .alpha(0.78F),
                    painter = painterResource(id = R.drawable.thebigscreen),
                    contentDescription = null
                )
            }
        }
    }
}
