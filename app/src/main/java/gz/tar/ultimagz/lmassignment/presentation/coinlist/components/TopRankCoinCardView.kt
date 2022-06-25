package gz.tar.ultimagz.lmassignment.presentation.coinlist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import gz.tar.ultimagz.lmassignment.presentation.coinlist.model.CoinModel

@Composable
fun TopRankCoinCardView(
    coin: CoinModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(coin.iconUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = coin.name,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                coin.symbol,
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h6,
            )

            Text(
                coin.name,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.subtitle2,
            )

            PriceChangeView(coin)
        }
    }
}