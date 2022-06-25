package gz.tar.ultimagz.lmassignment.presentation.coinlist.components

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import gz.tar.ultimagz.lmassignment.R
import gz.tar.ultimagz.lmassignment.utils.Constants

@Composable
fun InviteFriendCardView(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Card(
        modifier = modifier,
        backgroundColor = Color(0xFFC5E6FF),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_invite_friend),
                contentDescription = "ic_invite_friend",
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                val inviteString = createInviteString()

                ClickableText(
                    text = inviteString,
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.wrapContentHeight(),
                    onClick = {
                        inviteString
                            .getStringAnnotations(Constants.INVITE_FRIEND_TAG, it, it)
                            .firstOrNull()?.let { _ ->
                                Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, Uri.parse(Constants.INVITE_FRIEND_URL))
                                    type = "text/plain"
                                }.let { intent ->
                                    Intent.createChooser(intent, null)
                                }.run {
                                    context.startActivity(this@run)
                                }
                            }
                    }
                )
            }
        }
    }
}

@Composable
private fun createInviteString(): AnnotatedString {
    return buildAnnotatedString {
        val link = stringResource(R.string.invite_link_text)
        val msg = stringResource(R.string.invite_message_template, link)
        val startIndex = msg.indexOf(link)
        val endIndex = startIndex + link.length

        append(msg)
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Normal
            ),
            start = startIndex,
            end = endIndex
        )

        addStringAnnotation(
            tag = Constants.INVITE_FRIEND_TAG,
            annotation = Constants.INVITE_FRIEND_URL,
            start = startIndex,
            end = endIndex
        )
    }
}