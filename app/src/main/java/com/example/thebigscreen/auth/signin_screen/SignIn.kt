package com.example.thebigscreen.auth.signin_screen

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.thebigscreen.R
import com.example.thebigscreen.destinations.HomeDestination
import com.example.thebigscreen.ui.theme.AppPrimaryColor
import com.example.thebigscreen.ui.theme.lightBlue
import com.example.thebigscreen.util.Constants.ServerClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun SignInScreen(
    navigator: DestinationsNavigator,
    viewModel: SignInViewModel = hiltViewModel(),
) {

    val googleSignInState = viewModel.googleState.value


    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.googleSignIn(credentials)
            } catch (it: ApiException) {
                print(it)
            }
        }


    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signInState.collectAsState(initial = null)
    val navController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppPrimaryColor)   // changes here
            .padding(start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter your credential's to register",
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = Color.White,
            fontFamily = FontFamily.Serif
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = email,
            onValueChange = {
                email = it
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
//                containerColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

        )

        Spacer(modifier = Modifier.height(16.dp))

        var passwordVisible by remember {
            mutableStateOf(false)
        }

        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
//                containerColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = {
                Text(text = "Password")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = {
                    passwordVisible = !passwordVisible
                }) {
                    Icon(imageVector = image, contentDescription = "")
                }
            },
        )

        Button(
            onClick = {

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    scope.launch {
                        viewModel.loginUser(email, password)
                        /* TODO I add logic here to redirect to HomeScreen after successful user login */
                    }
                    navigator.navigate(HomeDestination())   // added
                } else {
                    Toast.makeText(
                        context,
                        "Please enter valid email and password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 0.dp, end = 0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Sign In",
                color = Color.White,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(7.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (state.value?.isLoading == true) {
                CircularProgressIndicator()
            }

        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "New User? Sign Up ",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = FontFamily.Serif
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "or connect with",
            fontWeight = FontWeight.Medium,
            color = Color.White
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestIdToken(ServerClient)
                    .build()


                val googleSingInClient = GoogleSignIn.getClient(context, gso)

                launcher.launch(googleSingInClient.signInIntent)

            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google Icon",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Unspecified
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            IconButton(onClick = {
                TODO()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "Facebook Icon",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Unspecified
                )
            }
            LaunchedEffect(key1 = state.value?.isSuccess) {
                scope.launch {
                    if (state.value?.isSuccess?.isNotEmpty() == true) {
                        val success = state.value?.isSuccess
                        Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                    }
                }
            }

            LaunchedEffect(key1 = state.value?.isError) {
                scope.launch {
                    if (state.value?.isError?.isNotEmpty() == true) {
                        val error = state.value?.isError
                        Toast.makeText(context, "${error}", Toast.LENGTH_LONG).show()
                    }
                }
            }

            LaunchedEffect(key1 = googleSignInState.success) {
                scope.launch {
                    if (googleSignInState.success != null) {
                        Toast.makeText(context, "Sign In Success", Toast.LENGTH_LONG).show()
                    }
                }
            }






            /* TODO the logic here is that after signing in with google the user will be redirected to HomeScreen */
            /* TODO due to below code the google sign in working */
            // Check Google sign-in success
            LaunchedEffect(key1 = googleSignInState.success) {
                if (googleSignInState.success != null) {
                    // Navigate to the profile screen
//                    navController.navigate(Screens.ProfileScreen.route)
                    navigator.navigate(HomeDestination())
                }
            }

        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (googleSignInState.loading) {
                CircularProgressIndicator()
            }
        }

    }


    /* Column(
         modifier = Modifier
             .fillMaxSize()
             .padding(40.dp),
         horizontalAlignment = Alignment.Start,
         verticalArrangement = Arrangement.SpaceEvenly
     ) {
         IconButton(onClick = {
             *//* to navigate back to signup screen *//*
            *//* TODO also add logic for not navigating to signup screen if user logged in *//*
            navigator.navigateUp()
        }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.arrow_back)
            )
        }

        Text(
            modifier = Modifier.width(200.dp),
            text = stringResource(R.string.welcome_back),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        var email by remember {
            mutableStateOf("")
        }
        TextField(
            value = email,
            onValueChange = { newText ->
                email = newText
            },
            label = {
                Text(text = stringResource(R.string.email_address))
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.sample_email),
                    modifier = Modifier.alpha(0.3f)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
        )

        var pass by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        TextField(
            value = pass,
            onValueChange = { newText ->
                pass = newText
            },
            label = {
                Text(text = stringResource(R.string.create_password))
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_your_password),
                    modifier = Modifier.alpha(0.3f)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = {
                    passwordVisible = !passwordVisible
                }) {
                    Icon(imageVector = image, contentDescription = "")
                }
            },
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.remember_me))

            var switchState by remember {
                mutableStateOf(false)
            }
            Switch(checked = switchState, onCheckedChange = {
                switchState = it
            })
        }

        Button(
            onClick = {
                      *//* navigate to HomeScreen after signin *//*
                      navigator.navigate(HomeDestination())
            },
            Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 255, green = 125, blue = 0),
                contentColor = Color.White
            )
        ) {
            Text(text = stringResource(R.string.sign_in), fontWeight = FontWeight.Bold)
        }
    }*/
}


@Preview(showSystemUi = true)
@Composable
fun SignInScreenPreview() {
//    SignInScreen()
}
