package gz.tar.ultimagz.lmassignment.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColors(
    val priceUp: Color = Color.Unspecified,
    val priceDown: Color = Color.Unspecified,
    val textButton: Color= Color.Unspecified,
    val divider: Color = Color.Unspecified,
    val indicator: Color = Color.Unspecified,
    val searchBoxBackground: Color = Color.Unspecified,
    val searchIconColor: Color = Color.Unspecified,
) {
    companion object {
        val LightAppColors = AppColors(
            priceUp = Color(0xFF13BC24),
            priceDown = Color(0xFFF82D2D),
            textButton = Color(0xFF38A0FF),
            divider = Color(0xFFEEEEEE),
            indicator = Color(0xFF38A0FF),
            searchBoxBackground = Color(0xFFEEEEEE),
            searchIconColor = Color(0xFFC4C4C4),
        )

        val DarkAppColors = AppColors(
            priceUp = Color(0xFF13BC24),
            priceDown = Color(0xFFF82D2D),
            textButton = Color(0xFF38A0FF),
            divider = Color(0xFFEEEEEE),
            indicator = Color(0xFF38A0FF),
            searchBoxBackground = Color(0xFF555555),
            searchIconColor = Color.White,
        )
    }
}

val LocalAppColors = compositionLocalOf { AppColors() }
