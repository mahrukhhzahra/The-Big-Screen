package com.example.thebigscreen.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thebigscreen.R
import com.example.thebigscreen.sharedComposables.BackButton
import com.example.thebigscreen.ui.theme.AppOnPrimaryColor
import com.example.thebigscreen.ui.theme.AppPrimaryColor
import com.example.thebigscreen.ui.theme.ButtonColor
import com.example.thebigscreen.util.Constants
import com.example.thebigscreen.util.FilmType
import com.example.thebigscreen.viewmodel.DetailsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Destination
@Composable
fun WatchProvidersScreen(
    navigator: DestinationsNavigator,
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    filmId: Int,
    filmType: FilmType,
    filmTitle: String
) {
    val filmID by remember { mutableIntStateOf(filmId) }
    val filmTYPE by remember { mutableStateOf(filmType) }
    val filmTITLE by remember { mutableStateOf(filmTitle) }
    val watchProvider = detailsViewModel.watchProviders.value

    LaunchedEffect(key1 = filmID) {
        detailsViewModel.getWatchProviders(filmID, filmTYPE)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppPrimaryColor)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 10.dp, top = 16.dp, bottom = 8.dp)
                .fillMaxWidth()
        ) {
            BackButton {
                navigator.navigateUp()
            }
        }

        Text(
            text = "$filmTITLE is available on",
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = AppOnPrimaryColor
        )

        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
            columns = GridCells.Fixed(3)
        ) {
            watchProvider?.provider?.rent?.forEach { rent ->
                item {
                    ShowWatchProvider(
                        name = rent.providerName,
                        logoPath = rent.logoPath
                    )
                }
            }
        }
    }
}

@Composable
fun ShowWatchProvider(name: String?, logoPath: String?) {
    Column(
        modifier = Modifier.padding(start = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CoilImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(70.dp),
            imageModel = "${Constants.BASE_POSTER_IMAGE_URL}/$logoPath",
            shimmerParams = ShimmerParams(
                baseColor = AppPrimaryColor,
                highlightColor = ButtonColor,
                durationMillis = 500,
                dropOff = 0.65F,
                tilt = 20F
            ),
            failure = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        modifier = Modifier.size(70.dp),
                        painter = painterResource(id = R.drawable.ic_user),
                        tint = Color.LightGray,
                        contentDescription = null
                    )
                }
            },
            previewPlaceholder = R.drawable.ic_user,
            contentScale = ContentScale.Crop,
            circularReveal = CircularReveal(duration = 1000),
            contentDescription = "cast image"
        )
        Text(
            text = name?.let { name } ?: "",
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = AppOnPrimaryColor.copy(alpha = 0.78F)
        )
    }
}


@Preview
@Composable
fun PreviewShowWatchProvider() {
    ShowWatchProvider(name = "horror", logoPath = "")
}