package com.rpfcoding.borutocharacterviewer.presentation.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rpfcoding.borutocharacterviewer.R
import com.rpfcoding.borutocharacterviewer.ui.theme.topAppBarBackgroundColor
import com.rpfcoding.borutocharacterviewer.ui.theme.topAppBarContentColor

@Composable
fun HomeTopBar(
    onSearchClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.title_explore),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon)
                )
            }
        }
    )
}

@Preview
@Composable
fun HomeTopBarPreview() {
    HomeTopBar {}
}

// This will not work because app bar theme is calculated in runtime not in compile time.
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeTopBarDarkPreview() {
    HomeTopBar {}
}
