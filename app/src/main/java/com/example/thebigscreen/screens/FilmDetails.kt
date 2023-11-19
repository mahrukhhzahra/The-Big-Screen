package com.example.thebigscreen.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayCircleOutline
import androidx.compose.material.icons.rounded.Reviews
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.thebigscreen.data.local.MyListMovie
import com.example.thebigscreen.model.Film
import com.example.thebigscreen.ui.theme.AppOnPrimaryColor
import com.example.thebigscreen.util.FilmType
import com.example.thebigscreen.viewmodel.DetailsViewModel
import com.example.thebigscreen.viewmodel.HomeViewModel
import com.example.thebigscreen.viewmodel.WatchListViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.text.SimpleDateFormat
import java.util.Date
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.thebigscreen.R
import com.example.thebigscreen.model.Cast
import com.example.thebigscreen.model.Genre
import com.example.thebigscreen.screens.destinations.ReviewsScreenDestination
import com.example.thebigscreen.screens.destinations.WatchProvidersScreenDestination
import com.example.thebigscreen.sharedComposables.BackButton
import com.example.thebigscreen.sharedComposables.ExpandableText
import com.example.thebigscreen.sharedComposables.MovieGenreChip
import com.example.thebigscreen.ui.theme.AppPrimaryColor
import com.example.thebigscreen.ui.theme.ButtonColor
import com.example.thebigscreen.util.Constants.BASE_BACKDROP_IMAGE_URL
import com.example.thebigscreen.util.Constants.BASE_POSTER_IMAGE_URL
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.ramcosta.composedestinations.annotation.Destination
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Destination
@Composable
fun MovieDetails(
    navigator: DestinationsNavigator,
    homeViewModel: HomeViewModel = hiltViewModel(),
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    watchListViewModel: WatchListViewModel = hiltViewModel(),
    currentFilm: Film,
    selectedFilmType: FilmType
) {
    var film by remember {
        mutableStateOf(currentFilm)
    }
    val filmType: FilmType = remember { selectedFilmType }

    val date = SimpleDateFormat.getDateTimeInstance().format(Date())
    val watchListMovie = MyListMovie(
        mediaId = film.id,
        imagePath = film.posterPath,
        title = film.title,
        releaseDate = film.releaseDate,
        rating = film.voteAverage,
        addedOn = date
    )

    val addedToList = watchListViewModel.addedToWatchList.value
    val similarFilms = detailsViewModel.similarMovies.value.collectAsLazyPagingItems()
    val filmCastList = detailsViewModel.filmCast.value

    LaunchedEffect(key1 = film) {
        detailsViewModel.getSimilarFilms(filmId = film.id, filmType)
        detailsViewModel.getFilmCast(filmId = film.id, filmType)
        watchListViewModel.exists(mediaId = film.id)
        detailsViewModel.getWatchProviders(film.id, selectedFilmType)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF180E36))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.33F)
        ) {
            val (
                backdropImage,
                backButton,
                movieTitleBox,
                moviePosterImage,
                translucentBr
            ) = createRefs()

            CoilImage(
                imageModel = "$BASE_BACKDROP_IMAGE_URL${film.backdropPath}",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                    .fillMaxHeight()
                    .constrainAs(backdropImage) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                failure = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(2.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.backdrop_not_available),
                            contentDescription = "no image"
                        )
                    }
                },
                shimmerParams = ShimmerParams(
                    baseColor = AppPrimaryColor,
                    highlightColor = ButtonColor,
                    durationMillis = 500,
                    dropOff = 0.65F,
                    tilt = 20F
                ),
                contentScale = ContentScale.Crop,
                contentDescription = "Header backdrop image",
            )

            BackButton(modifier = Modifier
                .constrainAs(backButton) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }) {
                navigator.navigateUp()
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color(0XFF180E36).copy(alpha = 0.5F),
                                Color(0XFF180E36)
                            ),
                            startY = 0.1F
                        )
                    )
                    .constrainAs(translucentBr) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(backdropImage.bottom)
                    }
            )

            Column(
                modifier = Modifier.constrainAs(movieTitleBox) {
                    start.linkTo(moviePosterImage.end, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    bottom.linkTo(moviePosterImage.bottom, margin = 10.dp)
                },
                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = when (filmType) {
                            FilmType.TVSHOW -> "Series"
                            FilmType.MOVIE -> "Movie"
                        },
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(size = 4.dp))
                            .background(Color.DarkGray.copy(alpha = 0.65F))
                            .padding(2.dp),
                        color = AppOnPrimaryColor.copy(alpha = 0.78F),
                        fontSize = 12.sp,
                    )
                    Text(
                        text = when (film.adult) {
                            true -> "18+"
                            else -> "PG"
                        },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clip(shape = RoundedCornerShape(size = 4.dp))
                            .background(
                                if (film.adult) Color(0xFFFF7070) else Color.DarkGray.copy(
                                    alpha = 0.65F
                                )
                            )
                            .padding(2.dp),
                        color = AppOnPrimaryColor.copy(alpha = 0.78F),
                        fontSize = 12.sp,
                    )
                }

                Text(
                    text = film.title,
                    modifier = Modifier
                        .padding(top = 2.dp, start = 4.dp, bottom = 4.dp)
                        .fillMaxWidth(0.5F),
                    maxLines = 2,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White.copy(alpha = 0.78F)
                )

                Text(
                    text = film.releaseDate,
                    modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White.copy(alpha = 0.56F)
                )

                RatingBar(
                    value = (film.voteAverage / 2).toFloat(),
                    modifier = Modifier.padding(start = 6.dp, bottom = 4.dp, top = 4.dp),
                    config = RatingBarConfig()
                        .style(RatingBarStyle.Normal)
                        .isIndicator(true)
                        .activeColor(Color(0XFFC9F964))
                        .hideInactiveStars(false)
                        .inactiveColor(Color.LightGray.copy(alpha = 0.3F))
                        .stepSize(StepSize.HALF)
                        .numStars(5)
                        .size(16.dp)
                        .padding(4.dp),
                    onValueChange = {},
                    onRatingChanged = {}
                )

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 4.dp, bottom = 8.dp)
                        .fillMaxWidth(0.42F),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Reviews,
                        tint = AppOnPrimaryColor,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                navigator.navigate(
                                    ReviewsScreenDestination(
                                        filmId = film.id,
                                        filmType = filmType,
                                        filmTitle = film.title
                                    )
                                )
                            },
                        contentDescription = "reviews icon"
                    )
                    IconButton(onClick = {
                        navigator.navigate(
                            WatchProvidersScreenDestination(
                                filmId = film.id,
                                filmType = filmType,
                                filmTitle = film.title
                            )
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.PlayCircleOutline,
                            tint = AppOnPrimaryColor,
                            contentDescription = "play icon"
                        )
                    }

                    val context = LocalContext.current
                    IconButton(onClick = {
                        if (addedToList != 0) {
                            watchListViewModel.removeFromWatchList(watchListMovie.mediaId)

                            Toast.makeText(
                                context, "Removed from watchlist", Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            watchListViewModel.addToWatchList(watchListMovie)
                            Toast.makeText(
                                context, "Added to watchlist", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {
                        Icon(
                            painter = painterResource(
                                id = if (addedToList != 0) R.drawable.ic_added_to_list
                                else R.drawable.ic_add_to_list
                            ),
                            tint = AppOnPrimaryColor,
                            contentDescription = "add to watch list icon"
                        )
                    }
                }
            }

            CoilImage(
                imageModel = "$BASE_POSTER_IMAGE_URL/${film.posterPath}",
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .width(115.dp)
                    .height(172.5.dp)
                    .constrainAs(moviePosterImage) {
                        top.linkTo(backdropImage.bottom)
                        bottom.linkTo(backdropImage.bottom)
                        start.linkTo(parent.start)
                    }, failure = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_not_available),
                            contentDescription = "no image"
                        )
                    }
                },
                shimmerParams = ShimmerParams(
                    baseColor = AppPrimaryColor,
                    highlightColor = ButtonColor,
                    durationMillis = 500,
                    dropOff = 0.65F,
                    tilt = 20F
                ),
                previewPlaceholder = R.drawable.popcorn,
                contentScale = ContentScale.Crop,
                circularReveal = CircularReveal(duration = 1000),
                contentDescription = "movie poster"
            )
        }

        LazyRow(
            modifier = Modifier
                .padding(top = (96).dp, bottom = 4.dp, start = 4.dp, end = 4.dp)
                .fillMaxWidth()
        ) {
            val filmGenres: List<Genre> = homeViewModel.filmGenres.filter { genre ->
                return@filter if (film.genreIds.isNullOrEmpty()) false else
                    film.genreIds!!.contains(genre.id)
            }
            filmGenres.forEach { genre ->
                item {
                    MovieGenreChip(
                        background = ButtonColor,
                        textColor = AppOnPrimaryColor,
                        genre = genre.name
                    )
                }
            }
        }

        ExpandableText(
            text = film.overview,
            modifier = Modifier
                .padding(top = 3.dp, bottom = 4.dp, start = 4.dp, end = 4.dp)
                .fillMaxWidth()
        )

        LazyColumn(
            horizontalAlignment = Alignment.Start
        ) {
            item {
                AnimatedVisibility(visible = (filmCastList.isNotEmpty())) {
                    Text(
                        text = "Cast",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = AppOnPrimaryColor,
                        modifier = Modifier.padding(start = 4.dp, top = 6.dp, bottom = 4.dp)
                    )
                }
            }
            item {
                LazyRow(modifier = Modifier.padding(4.dp)) {
                    filmCastList.forEach { cast ->
                        item { CastMember(cast = cast) }
                    }
                }
            }
            item {
                if (similarFilms.itemCount != 0) {
                    Text(
                        text = "Similar",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = AppOnPrimaryColor,
                        modifier = Modifier.padding(start = 4.dp, top = 6.dp, bottom = 4.dp)
                    )
                }
            }

            item {
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(similarFilms.itemSnapshotList) { thisMovie ->
                        CoilImage(
                            imageModel = "${BASE_POSTER_IMAGE_URL}/${thisMovie!!.posterPath}",
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
                                    Image(
                                        painter = painterResource(id = R.drawable.image_not_available),
                                        contentDescription = "no image"
                                    )
                                }
                            },
                            previewPlaceholder = R.drawable.popcorn,
                            contentScale = ContentScale.Crop,
                            circularReveal = CircularReveal(duration = 1000),
                            modifier = Modifier
                                .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .size(130.dp, 195.dp)
                                .clickable {
                                    film = thisMovie
                                },
                            contentDescription = "Movie item"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CastMember(cast: Cast?) {
    Column(
        modifier = Modifier.padding(end = 8.dp, top = 2.dp, bottom = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CoilImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(70.dp),
            imageModel = "$BASE_POSTER_IMAGE_URL/${cast!!.profilePath}",
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
            text = trimName(cast.name),
            maxLines = 1,
            color = AppOnPrimaryColor.copy(alpha = 0.5F),
            fontSize = 14.sp,
        )
        Text(
            text = trimName(cast.department),
            maxLines = 1,
            color = AppOnPrimaryColor.copy(alpha = 0.45F),
            fontSize = 12.sp,
        )
    }
}

fun trimName(name: String): String {
    return if (name.length <= 10) name else {
        name.removeRange(8..name.lastIndex) + "..."
    }
}