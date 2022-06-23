package gz.tar.ultimagz.lmassignment.presentation.coinlist

import androidx.compose.material.Colors
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gz.tar.ultimagz.domain.lmassignment.repository.CoinRepository
import gz.tar.ultimagz.lmassignment.presentation.coinlist.model.CoinInfoViewData
import gz.tar.ultimagz.lmassignment.presentation.coinlist.model.CoinModel
import gz.tar.ultimagz.lmassignment.presentation.coinlist.model.CoinViewData
import gz.tar.ultimagz.lmassignment.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val repository: CoinRepository
): ViewModel() {
    private var currentPage = 0
    var coinList = mutableStateOf<List<CoinModel>>(listOf())
    var selectedCoin = mutableStateOf<CoinInfoViewData?>(null)
    var loadError = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadCoinsPaginated()
    }

    fun loadCoinsPaginated() {
        viewModelScope.launch {
            val offset = currentPage * Constants.PAGE_SIZE
            repository.getCoins(offset, Constants.PAGE_SIZE)
                .map { CoinViewData.from(it) }
                .onStart {
                    loadError.value = false
                    isLoading.value = true
                    delay(2_000)
                }
                .onCompletion {
                    isLoading.value = false
                    loadError.value = false
                }
                .catch {
                    isLoading.value = false
                    loadError.value = true
                }
                .collect {
                    endReached.value = offset >= it.stats.totalCoins
                    coinList.value += it.coins.sortedBy { coin -> coin.rank }
                    currentPage++
                }
        }
    }

    fun select(coin: CoinModel, colors: Colors) {
        viewModelScope.launch {
            repository.getCoinInfo(coin.uuid)
                .collect {
                    selectedCoin.value = CoinInfoViewData.from(
                        coinInfo = it,
                        defaultNameColor = colors.onPrimary,
                        symbolColor = colors.onPrimary,
                    )
                }
        }
    }

    fun deselectCoin() {
        selectedCoin.value = null
    }
}