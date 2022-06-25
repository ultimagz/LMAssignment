package gz.tar.ultimagz.lmassignment.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import gz.tar.ultimagz.lmassignment.R

@Composable
fun NoResultBox(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.not_result_title),
            style = MaterialTheme.typography.h6
        )
        Text(
            text = stringResource(R.string.not_result_message),
            style = MaterialTheme.typography.subtitle1
        )
    }
}