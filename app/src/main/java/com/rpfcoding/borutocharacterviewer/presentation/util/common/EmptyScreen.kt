package com.rpfcoding.borutocharacterviewer.presentation.util.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.rpfcoding.borutocharacterviewer.R
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import com.rpfcoding.borutocharacterviewer.ui.theme.DarkGray
import com.rpfcoding.borutocharacterviewer.ui.theme.LightGray
import com.rpfcoding.borutocharacterviewer.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import com.rpfcoding.borutocharacterviewer.ui.theme.SMALL_PADDING
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<HeroEntity>? = null
) {
    var message by remember {
        mutableStateOf("Find your favorite Hero!")
    }

    var icon by remember {
        mutableStateOf(R.drawable.ic_search_hero)
    }

    if(error != null) {
        message = parseErrorMessage(error = error)
        icon = R.drawable.ic_network_error
    }

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(
        alphaAnim = alphaAnim,
        icon = icon,
        message = message,
        error = error,
        heroes = heroes
    )
}

@Composable
fun EmptyContent(
    alphaAnim: Float = 1f,
    @DrawableRes icon: Int = R.drawable.ic_network_error,
    message: String,
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<HeroEntity>? = null
) {

    var isRefreshing by remember {
        mutableStateOf(false)
    }

    SwipeRefresh(
        swipeEnabled = error != null,
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            isRefreshing = true
            heroes?.refresh()
            isRefreshing = false
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(size = NETWORK_ERROR_ICON_HEIGHT)
                    .alpha(alpha = alphaAnim),
                painter = painterResource(id = icon),
                contentDescription = stringResource(id = R.string.network_error_icon),
                tint = if(isSystemInDarkTheme()) LightGray else DarkGray
            )
            Text(
                modifier = Modifier
                    .padding(top = SMALL_PADDING)
                    .alpha(alpha = alphaAnim),
                text = message,
                color = if (isSystemInDarkTheme()) LightGray else DarkGray,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        }

    }
}

fun parseErrorMessage(error: LoadState.Error): String {
    return when (error.error) {
        is SocketTimeoutException -> {
            "Server Unavailable."
        }
        is ConnectException -> {
            "Internet Unavailable."
        }
        else -> {
            "Something went wrong."
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenServerUnavailablePreview() {
    EmptyContent(message = parseErrorMessage(LoadState.Error(error = SocketTimeoutException())))
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenServerUnavailableDarkPreview() {
    EmptyContent(message = parseErrorMessage(LoadState.Error(error = SocketTimeoutException())))
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenInternetUnavailablePreview() {
    EmptyContent(message = parseErrorMessage(LoadState.Error(error = ConnectException())))
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenInternetUnavailableDarkPreview() {
    EmptyContent(message = parseErrorMessage(LoadState.Error(error = ConnectException())))
}