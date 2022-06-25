package gz.tar.ultimagz.lmassignment.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import gz.tar.ultimagz.lmassignment.R

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
                text = stringResource(R.string.could_not_load_data),
                style = MaterialTheme.typography.subtitle1
            )

            TextButton(
                contentPadding = PaddingValues(2.dp),
                onClick = { onRetryClick?.invoke() }
            ) {
                Text(
                    text = stringResource(R.string.try_again),
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}