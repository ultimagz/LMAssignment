package gz.tar.ultimagz.lmassignment.presentation.coinlist.components

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import gz.tar.ultimagz.lmassignment.R
import gz.tar.ultimagz.lmassignment.presentation.coinlist.CoinItem
import gz.tar.ultimagz.lmassignment.presentation.coinlist.CoinListViewModel
import gz.tar.ultimagz.lmassignment.presentation.coinlist.model.CoinModel
import gz.tar.ultimagz.lmassignment.presentation.components.ErrorBox
import gz.tar.ultimagz.lmassignment.presentation.components.LoadingBox
import gz.tar.ultimagz.lmassignment.presentation.components.NoResultBox

@ExperimentalFoundationApi
@Composable
fun CoinListView(
    viewModel: CoinListViewModel = hiltViewModel(),
    onCoinSelected: ((CoinModel) -> Unit)? = null,
) {
    val topRankCoins by remember { viewModel.topRankCoins }
    val coinList by remember { viewModel.coinList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }
    val isSearch by remember { viewModel.isSearch }
    val configuration = LocalConfiguration.current

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        if (!isSearch && !isLoading && topRankCoins.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Box {
                    Text(
                        text = stringResource(R.string.top_3_rank_section_title),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onBackground),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    topRankCoins.forEach {
                        TopRankCoinCardView(
                            coin = it,
                            modifier = Modifier
                                .widthIn(120.dp, 140.dp)
                                .clickable {
                                    onCoinSelected?.invoke(it)
                                },
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            Box {
                Text(
                    text = stringResource(R.string.coin_list_section_title),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onBackground),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

        val itemCount = coinList.size
        if (itemCount > 0) {
            val shouldLoadMore = !loadError && !isLoading && !endReached
            val gridSize = when(configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> 3
                else -> 1
            }

            gridItems(
                data = coinList,
                columnCount = gridSize,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) { item, index ->
//                Log.e("shouldLoadMore", "!$loadError && !$isLoading && !$endReached")
                if (index >= itemCount - gridSize && shouldLoadMore) {
                    viewModel.loadCoinsPaginated()
                }

                when(item) {
                    is CoinItem.CoinData -> {
                        CoinCardView(
                            coin = item.data,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onCoinSelected?.invoke(item.data)
                                },
                        )
                    }
                    is CoinItem.InviteFriend -> {
                        InviteFriendCardView(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        if (isLoading) {
            item {
                LoadingBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 12.dp)
                )
            }
        } else if (isSearch && coinList.isEmpty()) {
            item {
                NoResultBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillParentMaxHeight()
                        .padding(vertical = 12.dp)
                )
            }
        } else if (loadError) {
            item {
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
        } else {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

fun <T> LazyListScope.gridItems(
    data: List<T>,
    columnCount: Int,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    itemContent: @Composable BoxScope.(T, Int) -> Unit,
) {
    val size = data.count()
    val rows = if (size == 0) 0 else 1 + (size - 1) / columnCount
    items(rows, key = { it.hashCode() }) { rowIndex ->
        Row(
            horizontalArrangement = horizontalArrangement,
            modifier = modifier
        ) {
            for (columnIndex in 0 until columnCount) {
                val itemIndex = rowIndex * columnCount + columnIndex
                if (itemIndex < size) {
                    Box(
                        modifier = Modifier.weight(1F, fill = true),
                        propagateMinConstraints = true
                    ) {
                        itemContent(data[itemIndex], itemIndex)
                    }
                } else {
                    Spacer(Modifier.weight(1F, fill = true))
                }
            }
        }
    }
}