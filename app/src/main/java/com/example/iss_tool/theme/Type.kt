package com.example.iss_tool.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.iss_tool.R

val sourceSansProFontFamily = FontFamily(
    Font(R.font.source_sans_pro_regular, FontWeight.Normal),
    Font(R.font.source_sans_pro_bold, FontWeight.Bold),
    Font(R.font.source_sans_pro_bold, FontWeight.SemiBold)
    // Add more font weights if necessary
)

val customTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = sourceSansProFontFamily,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    ),
    bodyMedium = TextStyle(
        fontFamily = sourceSansProFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    ),
    bodySmall = TextStyle(
        fontFamily = sourceSansProFontFamily,
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold
    ),
)

