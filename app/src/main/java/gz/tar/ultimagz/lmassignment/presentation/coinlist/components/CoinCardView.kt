package gz.tar.ultimagz.lmassignment.presentation.coinlist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import gz.tar.ultimagz.lmassignment.presentation.coinlist.model.CoinModel

@Composable
fun CoinCardView(
    coin: CoinModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
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
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.h6,
                )
                Text(
                    coin.symbol,
                    color = MaterialTheme.colors.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.subtitle2,
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.wrapContentWidth(),
                horizontalAlignment = Alignment.End,
            ) {
                Text(String.format("$%.5f", coin.price.toBigDecimal()))

                PriceChangeView(coin)
            }
        }
    }
}