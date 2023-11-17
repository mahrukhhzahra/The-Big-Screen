package com.example.thebigscreen.sharedComposables

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thebigscreen.ui.theme.ButtonColor
import com.example.thebigscreen.ui.theme.AppOnPrimaryColor

@Composable
fun BackButton(modifier: Modifier = Modifier, onClick: () -> Unit) {

    FloatingActionButton(
        modifier = modifier.size(42.dp),
        containerColor = ButtonColor,
        contentColor = AppOnPrimaryColor,
        onClick = { onClick() }) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "back icon"
        )
    }
}