package gz.tar.ultimagz.lmassignment.presentation.coinlist.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import gz.tar.ultimagz.lmassignment.R
import gz.tar.ultimagz.lmassignment.presentation.coinlist.CoinCardView
import gz.tar.ultimagz.lmassignment.presentation.coinlist.CoinListViewModel
import gz.tar.ultimagz.lmassignment.presentation.coinlist.model.CoinModel

@Composable
fun CoinListView(
    viewModel: CoinListViewModel = hiltViewModel(),
    onCoinSelected: ((CoinModel) -> Unit)? = null,
) {
    val coinList by remember { viewModel.coinList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    val itemCount = coinList.size

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.coin_list_title),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onBackground),
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        items(itemCount) {
            val shouldLoadMore = !loadError && !isLoading && !endReached
            if (it >= itemCount - 1 && shouldLoadMore) {
                viewModel.loadCoinsPaginated()
            }

            CoinCardView(coin = coinList[it], coinSelected = { coin ->
                onCoinSelected?.invoke(coin)
            })

            if (it < itemCount - 1) {
                Divider(
                    modifier = Modifier.height(12.dp),
                    color = Color.Transparent,
                )
            }
        }

        item {
            if (isLoading) {
                LoadingBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 12.dp)
                )
            }

            if (loadError) {
                ErrorBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 12.dp),
                    onRetryClick = {
                        viewModel.loadCoinsPaginated()
                    }
                )
            }
        }
    }
}