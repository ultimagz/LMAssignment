package gz.tar.ultimagz.lmassignment.presentation.coinInfo

import androidx.compose.material.Colors
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gz.tar.ultimagz.domain.lmassignment.repository.CoinRepository
import gz.tar.ultimagz.lmassignment.presentation.coinlist.model.CoinInfoViewData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinInfoViewModel @Inject constructor(
    private val repository: CoinRepository
): ViewModel() {
    private var coin = ""
    private var colorsState: Colors? = null

    var coinInfo = mutableStateOf<CoinInfoViewData?>(null)
        private set

    var loadError = mutableStateOf(false)
        private set

    var isLoading = mutableStateOf(false)
        private set

    fun getSelectCoinInfo() {
        getCoinInfo(coin, colorsState)
    }

    fun selectCoin(uuid: String, colors: Colors?) {
        viewModelScope.launch {
            coin = uuid
            colorsState = colors
        }
    }

    private fun getCoinInfo(uuid: String, colors: Colors?) {
        viewModelScope.launch {
            coin = uuid

            repository.getCoinInfo(uuid)
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
                    coinInfo.value = CoinInfoViewData.from(
                        coinInfo = it,
                        defaultNameColor = colors?.onPrimary ?: Color.Black,
                        symbolColor = colors?.onPrimary ?: Color.Black,
                    )
                }
        }
    }

    fun deselectCoin() {
        coin = ""
        colorsState = null
        coinInfo.value = null
    }
}