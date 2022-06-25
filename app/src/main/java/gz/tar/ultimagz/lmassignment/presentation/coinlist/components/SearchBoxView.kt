package gz.tar.ultimagz.lmassignment.presentation.coinlist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import gz.tar.ultimagz.lmassignment.R
import gz.tar.ultimagz.lmassignment.theme.LocalAppColors

@Composable
fun SearchBoxView(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    onValueChange: (TextFieldValue) -> Unit,
    onClearText: (() -> Unit)? = null
) {
    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    val appColors = LocalAppColors.current

    Box(
        modifier = modifier
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                onValueChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textStyle = MaterialTheme.typography.body2,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFF9E9E9E)
                )
            },
            leadingIcon = {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                    contentDescription = "ic_search",
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(appColors.searchIconColor)
                )
            },
            trailingIcon = {
                if (text.text.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            text = TextFieldValue("")
                            onClearText?.invoke()
                        }
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                            contentDescription = "ic_delete",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {

            }),
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.onPrimary,
                cursorColor = MaterialTheme.colors.onPrimary,
                backgroundColor = appColors.searchBoxBackground
            ),
        )
    }
}