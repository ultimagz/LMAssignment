package gz.tar.ultimagz.lmassignment.presentation.coinlist.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import gz.tar.ultimagz.lmassignment.presentation.coinlist.CoinListViewModel
import gz.tar.ultimagz.lmassignment.theme.TextButton

@Composable
fun ErrorBox(
    modifier: Modifier = Modifier,
    onRetryClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Could not load data",
                style = MaterialTheme.typography.subtitle1
            )

            TextButton(
                contentPadding = PaddingValues(2.dp),
                onClick = { onRetryClick?.invoke() }
            ) {
                Text(
                    text = "Try again",
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}