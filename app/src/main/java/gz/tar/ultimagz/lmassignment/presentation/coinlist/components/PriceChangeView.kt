package gz.tar.ultimagz.lmassignment.presentation.coinlist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gz.tar.ultimagz.lmassignment.R
import gz.tar.ultimagz.lmassignment.presentation.coinlist.model.CoinModel
import gz.tar.ultimagz.lmassignment.theme.LocalAppColors

@Composable
fun PriceChangeView(
    coin: CoinModel,
) {
    val appColors = LocalAppColors.current
    Row(verticalAlignment = Alignment.CenterVertically) {
        val (changeSymbol: Int, changeColor: Color) = if (coin.change.toFloat() > 0) {
            Pair(R.drawable.ic_arrowup, appColors.priceUp)
        } else {
            Pair(R.drawable.ic_arrowdown, appColors.priceDown)
        }

        Image(
            imageVector = ImageVector.vectorResource(id = changeSymbol),
            contentDescription = "",
            colorFilter = ColorFilter.tint(color = changeColor),
            modifier = Modifier.size(12.dp)
        )

        Spacer(modifier = Modifier.width(2.dp))

        Text(
            String.format("%.2f", coin.change.toFloat()),
            fontSize = 12.sp,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.caption.copy(
                color = changeColor,
                fontWeight = FontWeight.Bold
            ),
        )
    }
}