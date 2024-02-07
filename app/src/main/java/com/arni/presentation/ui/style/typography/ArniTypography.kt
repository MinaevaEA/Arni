package com.arni.presentation.ui.style.typography

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.arni.R


@Immutable
data class ArniTypography(
    val large_title: ArniFont,
    val title_1: ArniFont,
    val title_2: ArniFont,
    val title_3: ArniFont,
    val headline: ArniFont,
    val body: ArniFont,
    val callout: ArniFont,
    val subhead: ArniFont,
    val footnote: ArniFont,
    val caption_1: ArniFont,
    val caption_2: ArniFont,
)

val mecFontFamily = FontFamily(
    Font(R.font.ubuntubold, FontWeight.W700),
    Font(R.font.ubunturegular, FontWeight.W400),
)

@Immutable
class ArniFont(
    fontSize: TextUnit,
    lineHeight: TextUnit
) {

    val regular = TextStyle(
        fontFamily = mecFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = fontSize,
        lineHeight = lineHeight,
    )
    val light = TextStyle(
        fontFamily = mecFontFamily,
        fontWeight = FontWeight.W300,
        fontSize = fontSize,
        lineHeight = lineHeight,
    )
    val medium = TextStyle(
        fontFamily = mecFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = fontSize,
        lineHeight = lineHeight,
    )
    val semibold = TextStyle(
        fontFamily = mecFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = fontSize,
        lineHeight = lineHeight,
    )

    val bold = TextStyle(
        fontFamily = mecFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = fontSize,
        lineHeight = lineHeight
    )

}

val LocalArniTypography = staticCompositionLocalOf<ArniTypography> {
    error("No typography provided")
}

val arniTypography = ArniTypography(
    large_title = ArniFont(
        fontSize = 50.sp,
        lineHeight = 58.sp
    ),
    title_1 = ArniFont(
        fontSize = 28.sp,
        lineHeight = 34.sp
    ),
    title_2 = ArniFont(
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    title_3 = ArniFont(
        fontSize = 20.sp,
        lineHeight = 24.sp
    ),
    headline = ArniFont(
        fontSize = 17.sp,
        lineHeight = 22.sp
    ),
    body = ArniFont(
        fontSize = 17.sp,
        lineHeight = 22.sp
    ),
    callout = ArniFont(
        fontSize = 16.sp,
        lineHeight = 21.sp
    ),
    subhead = ArniFont(
        fontSize = 15.sp,
        lineHeight = 20.sp
    ),
    footnote = ArniFont(
        fontSize = 13.sp,
        lineHeight = 18.sp
    ),
    caption_1 = ArniFont(
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    caption_2 = ArniFont(
        fontSize = 11.sp,
        lineHeight = 13.sp
    )
)
