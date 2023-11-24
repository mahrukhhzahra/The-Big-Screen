/*
package com.example.movieapp_jetpackcompose.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.thebigscreen.R
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun WelcomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.welcome),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(id = R.string.intro_message),
                    fontSize = 20.sp,
                    modifier = Modifier.alpha(.3f)
                )

                Column(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://creazilla-store.fra1.digitaloceanspaces.com/cliparts/76522/hot-air-balloon-clipart-xl.png",
                        contentDescription = stringResource(id = R.string.welcome_screen_image)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { */
/*TODO*//*
 },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .absolutePadding(bottom = 5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(red = 255, green = 125, blue = 0),
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = stringResource(R.string.go_to_sign_in))
                    }

                    OutlinedButton(
                        onClick = {
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .absolutePadding(bottom = 5.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.no_account_yet_msg),
                            color = Color(red = 255, green = 125, blue = 0)
                        )
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}*/
