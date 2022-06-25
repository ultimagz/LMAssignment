package gz.tar.ultimagz.lmassignment.presentation.coinlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import gz.tar.ultimagz.lmassignment.R
import gz.tar.ultimagz.lmassignment.presentation.coinlist.model.CoinModel
import gz.tar.ultimagz.lmassignment.theme.PriceDown
import gz.tar.ultimagz.lmassignment.theme.PriceUp

@Composable
fun CoinCardView(
    coin: CoinModel,
    coinSelected: ((CoinModel) -> Unit)? = null,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                coinSelected?.invoke(coin)
            },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(coin.iconUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = coin.name,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    coin.name,
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.h6,
                )
                Text(
                    coin.symbol,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.subtitle2,
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.wrapContentWidth(),
                horizontalAlignment = Alignment.End,
            ) {
                Text(String.format("$%.5f", coin.price.toBigDecimal()))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    val (changeSymbol: Int, changeColor: Color) = if (coin.change.toFloat() > 0) {
                        Pair(R.drawable.ic_arrowup, Color.PriceUp)
                    } else {
                        Pair(R.drawable.ic_arrowdown, Color.PriceDown)
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
        }
    }
}