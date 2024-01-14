package com.example.arni.ui.style

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.arni.presentation.ui.style.color.ArniColors

val lightPalette = ArniColors(
    neutral_0 = Color(0xFFFFFFFF),
    neutral_100 = Color(0xFFF2F2F2),
    neutral_200 = Color(0xFFE5E5E5),
    neutral_300 = Color(0xFFCCCCCC),
    neutral_400 = Color(0xFFB2B2B2),
    neutral_500 = Color(0xFF999999),
    neutral_600 = Color(0xFF808080),
    neutral_700 = Color(0xFF666666),
    neutral_800 = Color(0xFF4D4D4D),
    neutral_900 = Color(0xFF000000),

    black_100 = Color(0xFF272729),
    white_100 = Color(0xFFFFFFFF),

    info = Color(0xFF73B9FF),
    success = Color(0xFF49C46D),
    warning = Color(0xFFFFC526),
    error = Color(0xFFF05454),
    error_bg = Color(0xFFFCEBEB)
)

val darkPalette = ArniColors(
    neutral_0 = Color(0xFFFFFFFF),
    neutral_100 = Color(0xFFF2F2F2),
    neutral_200 = Color(0xFFE5E5E5),
    neutral_300 = Color(0xFFCCCCCC),
    neutral_400 = Color(0xFFB2B2B2),
    neutral_500 = Color(0xFF999999),
    neutral_600 = Color(0xFF808080),
    neutral_700 = Color(0xFF666666),
    neutral_800 = Color(0xFF4D4D4D),
    neutral_900 = Color(0xFF000000),

    black_100 = Color(0xFF272729),
    white_100 = Color(0xFFFFFFFF),

    info = Color(0xFF73B9FF),
    success = Color(0xFF49C46D),
    warning = Color(0xFFFFC526),
    error = Color(0xFFF05454),
    error_bg = Color(0xFFFCEBEB)
)

val LocalArniColors = staticCompositionLocalOf<ArniColors> {
    error("no colors provided")
}
