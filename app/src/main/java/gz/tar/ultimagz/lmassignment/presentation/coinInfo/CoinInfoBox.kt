package gz.tar.ultimagz.lmassignment.presentation.coinInfo

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import de.charlex.compose.HtmlText
import gz.tar.ultimagz.lmassignment.presentation.coinlist.model.CoinInfoViewData
import gz.tar.ultimagz.lmassignment.presentation.components.ErrorBox
import gz.tar.ultimagz.lmassignment.presentation.components.LoadingBox
import gz.tar.ultimagz.lmassignment.theme.LocalAppColors
import gz.tar.ultimagz.lmassignment.utils.formatToShortScale

@ExperimentalMaterialApi
@Composable
fun CoinInfoBox(
    modifier: Modifier = Modifier,
    viewModel: CoinInfoViewModel = hiltViewModel()
) {
    val coinInfo by remember { viewModel.coinInfo }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    Box(modifier = modifier) {
        if (isLoading) {
            LoadingBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(vertical = 12.dp)
            )
        } else if (loadError) {
            ErrorBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(vertical = 12.dp),
                onRetryClick = {
                    viewModel.getSelectCoinInfo()
                }
            )
        } else {
            coinInfo?.let {
                CoinInfoContent(coin = it)
            }
        }
    }
}

@Composable
fun CoinInfoContent(
    coin: CoinInfoViewData
) {
    val context = LocalContext.current
    val appColors = LocalAppColors.current

    Column {
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(coin.iconUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = coin.name.toString(),
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    coin.name,
                    style = MaterialTheme.typography.h6,
                )
                Text(
                    String.format("PRICE $%.2f", coin.price.toBigDecimal()),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.subtitle2,
                )
                Text(
                    String.format("MARKET CAP %s", coin.marketCap.toBigDecimal().formatToShortScale()),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.subtitle2,
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        HtmlText(
            text = coin.description,
            style = MaterialTheme.typography.subtitle2.copy(
                color = Color(0xFF999999)
            ),
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .padding(horizontal = 24.dp)
        )

        Divider(
            thickness = 1.dp,
            color = appColors.divider
        )

        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(coin.websiteUrl))
                context.startActivity(intent)
            }
        ) {
            Text(
                text = "GO TO WEBSITE",
                style = MaterialTheme.typography.button
            )
        }
    }
}