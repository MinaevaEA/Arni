package pro.midev.mec.presentation.ui.style

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.example.arni.ui.style.LocalArniColors
import com.arni.presentation.ui.style.typography.LocalArniTypography
import com.arni.presentation.ui.style.typography.ArniTypography
import com.arni.presentation.ui.style.color.ArniColors
import com.example.arni.ui.style.darkPalette
import com.example.arni.ui.style.lightPalette
import com.arni.presentation.ui.style.typography.arniTypography


@Composable
fun ArniTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val palette = if (darkTheme) darkPalette else lightPalette
    val rippleIndications = rememberRipple()

    CompositionLocalProvider(
        LocalArniColors provides palette,
        LocalArniTypography provides arniTypography,
        LocalIndication provides rippleIndications,
        content = content
    )

}

object ArniTheme {
    val colors: ArniColors
        @Composable
        @ReadOnlyComposable
        get() = LocalArniColors.current

    val typography: ArniTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalArniTypography.current
}
