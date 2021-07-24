package com.john.kai.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.john.kai.R

private val MerriweatherSans = FontFamily(
    Font(R.font.merriweather_bold, FontWeight.Bold),
    Font(R.font.merriweather_bold_italic, FontWeight.Bold, FontStyle.Italic)
)

private val LibreFranklin = FontFamily(
    Font(R.font.librefranklin_medium, FontWeight.Medium),
    Font(R.font.librefranklin_semibold, FontWeight.SemiBold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h5 = TextStyle(
        fontFamily = MerriweatherSans,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        fontStyle = FontStyle.Italic,
    ),
    h4 = TextStyle(
        fontFamily = MerriweatherSans,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = LibreFranklin,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = KaiOrange
    ),
    body1 = TextStyle(
        fontFamily = LibreFranklin,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )
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
    )
    */
)