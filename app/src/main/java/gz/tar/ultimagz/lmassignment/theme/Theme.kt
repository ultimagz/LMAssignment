package gz.tar.ultimagz.lmassignment.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFF333333),
    primaryVariant = Color(0x0C0C0C),
    onPrimary = Color.White,
    secondary = Color(0xFF3C3C3C),
    secondaryVariant = Color(0xFF161616),
    onSecondary = Color.White,
    background = Color(0xFF333333),
    onBackground = Color.White,
    surface = Color(0xFF3C3C3C),
    onSurface = Color.White
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = Color(0xF5F5F5),
    onPrimary = Color.Black,
    secondary = Color(0xFFF9F9F9),
    secondaryVariant = Color(0xFFC6C6C6),
    onSecondary = Color(0xFF333333),
    background = Color.White,
    onBackground = Color.Black,
    surface = Color(0xFFF9F9F9),
    onSurface = Color(0xFF333333)
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

//    CompositionLocalProvider()

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}