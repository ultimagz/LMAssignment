package gz.tar.ultimagz.lmassignment.presentation.coinlist

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import gz.tar.ultimagz.lmassignment.R
import gz.tar.ultimagz.lmassignment.presentation.coinInfo.CoinInfoBox
import gz.tar.ultimagz.lmassignment.presentation.coinInfo.CoinInfoViewModel
import gz.tar.ultimagz.lmassignment.presentation.coinlist.components.CoinListView
import gz.tar.ultimagz.lmassignment.presentation.coinlist.components.SearchBoxView
import gz.tar.ultimagz.lmassignment.theme.LocalAppColors
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CoinListScreen(
    navController: NavController,
    coinListviewModel: CoinListViewModel = hiltViewModel(),
    coinInfoViewModel: CoinInfoViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    val coroutineScope = rememberCoroutineScope()
    val themeColors = MaterialTheme.colors
    val appColors = LocalAppColors.current

    ModalBottomSheetLayout(
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        sheetContent = {
            CoinInfoBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.65f),
            )
        },
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
    ) {
        Column {
            SearchBoxView(
                modifier = Modifier.padding(16.dp),
                placeholder = stringResource(R.string.placeholder_search),
                onValueChange = {
                    coinListviewModel.searchCoins(it.text)
                },
                onClearText = {
                    coinListviewModel.searchCoins("")
                }
            )

            Divider(
                modifier = Modifier.height(1.dp),
                color = Color.DarkGray,
            )

            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = false),
                indicator = { state, trigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = trigger,
                        contentColor = appColors.textButton,
                        scale = true
                    )
                },
                onRefresh = { coinListviewModel.refresh() },
            ) {
                CoinListView(
                    onCoinSelected = {
                        coroutineScope.launch {
                            coinInfoViewModel.selectCoin(uuid = it.uuid, colors = themeColors)
                            modalBottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
                        }
                    },
                )
            }
        }
    }

    LaunchedEffect(modalBottomSheetState) {
        snapshotFlow { modalBottomSheetState.isVisible }
            .onEach {
                focusManager.clearFocus()
            }
            .collect { isVisible ->
                if (isVisible) {
                    coinInfoViewModel.getSelectCoinInfo()
                } else {
                    coinInfoViewModel.deselectCoin()
                }
            }
    }

    BackHandler {
        if (modalBottomSheetState.isVisible) {
            coroutineScope.launch {
                modalBottomSheetState.hide()
            }
        } else {
            navController.popBackStack()
        }
    }
}
