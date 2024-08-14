package com.tei.snapshop.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tei.snapshop.R

// Set of Material typography styles to start with
val interFont = FontFamily(
    Font(R.font.inter_regular)
)
val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)