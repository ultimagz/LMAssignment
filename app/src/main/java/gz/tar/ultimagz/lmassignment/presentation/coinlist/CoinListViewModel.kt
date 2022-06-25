package gz.tar.ultimagz.lmassignment.presentation.coinlist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gz.tar.ultimagz.domain.lmassignment.repository.CoinRepository
import gz.tar.ultimagz.lmassignment.presentation.coinlist.model.CoinModel
import gz.tar.ultimagz.lmassignment.presentation.coinlist.model.CoinViewData
import gz.tar.ultimagz.lmassignment.utils.Constants
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CoinItem {
    data class CoinData(val data: CoinModel): CoinItem()
    data class InviteFriend(val index: Int): CoinItem()
}


@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val repository: CoinRepository
): ViewModel() {
    private var searchName = ""
    private var currentPage = 0
    private var actualItemIndex = 1
    private var nextInviteIndex = 5
    private var job: Job? = null
    private var autoRefreshJob: Job? = null

    var topRankCoins = mutableStateOf<List<CoinModel>>(listOf())
        private set

    var coinList = mutableStateOf<List<CoinItem>>(listOf())
        private set

    var loadError = mutableStateOf(false)
        private set

    var isLoading = mutableStateOf(false)
        private set

    var endReached = mutableStateOf(false)
        private set

    var isSearch = mutableStateOf(false)
        private set

    init {
        refresh()
    }

    fun refresh() {
        resetData()
        loadCoinsPaginated()
    }

    fun loadCoinsPaginated() {
        job?.cancel()
        job = viewModelScope.launch {
            doSearch()
            refreshScheduleUpdate()
        }
    }

    fun searchCoins(name: String) {
        job?.cancel()
        job = viewModelScope.launch {
            delay(500)

            searchName = name
            isSearch.value = name.isNotEmpty()

            resetData()
            doSearch()
        }
    }

    private fun resetData() {
        currentPage = 0
        actualItemIndex = 1
        nextInviteIndex = 5
        coinList.value = listOf()
    }

    private suspend fun doSearch() {
        val offset = currentPage * Constants.PAGE_SIZE
        repository.getCoins(searchName, offset, Constants.PAGE_SIZE)
            .map { CoinViewData.from(it) }
            .onStart {
                loadError.value = false
                isLoading.value = true
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
                endReached.value = (offset + it.coins.size) >= it.stats.totalCoins

                val sortedCoins = it.coins.sortedBy { coin -> coin.rank }

                if (currentPage == 0 && !isSearch.value) {
                    topRankCoins.value = sortedCoins.take(3)
                    coinList.value += createListWithInviteItem(sortedCoins.drop(3))
                } else {
                    coinList.value += createListWithInviteItem(sortedCoins)
                }

                currentPage++
            }
    }

    private fun createListWithInviteItem(list: List<CoinModel>): List<CoinItem> {
        return list.foldIndexed(listOf()) { index, acc, item ->
            val newAcc = if (actualItemIndex == nextInviteIndex) {
                nextInviteIndex *= 2
                acc + listOf(CoinItem.CoinData(item), CoinItem.InviteFriend(index))
            } else {
                acc + CoinItem.CoinData(item)
            }

            actualItemIndex++
            newAcc
        }
    }

    private fun refreshScheduleUpdate() {
        autoRefreshJob?.cancel()
        autoRefreshJob = viewModelScope.launch {
            while (true) {
                delay(10_000)
                repository.getCoins(searchName, 0, currentPage * Constants.PAGE_SIZE)
                    .map { CoinViewData.from(it) }
                    .onStart {
                        loadError.value = false
                    }
                    .onCompletion {
                        loadError.value = false
                    }
                    .catch {
                        loadError.value = true
                    }
                    .collect {
                        val sortedCoins = it.coins.sortedBy { coin -> coin.rank }
                        topRankCoins.value = sortedCoins.take(3)
                        coinList.value = createListWithInviteItem(sortedCoins.drop(3))
                    }
            }
        }
    }
}