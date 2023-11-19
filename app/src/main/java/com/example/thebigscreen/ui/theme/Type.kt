package com.example.thebigscreen.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.thebigscreen.R

/*
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    */
/* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    *//*

)*/


val quickSandFontFamily = FontFamily(
    Font(R.font.quicksand_regular, weight = FontWeight.Normal),
    Font(R.font.quicksand_light, weight = FontWeight.Light),
    Font(R.font.quicksand_medium, weight = FontWeight.Medium),
    Font(R.font.quicksand_semibold, weight = FontWeight.SemiBold),
    Font(R.font.quicksand_bold, weight = FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleMedium = TextStyle(
        fontFamily = quickSandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = quickSandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = quickSandFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = quickSandFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),
//    defaultFontFamily = quickSandFontFamily

    /* Other default text styles to override
     button = TextStyle(
         fontFamily = FontFamily.Default,
         fontWeight = FontWeight.W500,
         fontSize = 14.sp
     ),
     caption = TextStyle(
         fontFamily = FontFamily.Default,
         fontWeight = FontWeight.Normal,
         fontSize = 12.sp
     )*/
)