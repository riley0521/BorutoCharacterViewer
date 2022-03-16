package com.rpfcoding.borutocharacterviewer.presentation.screens.search

import androidx.compose.runtime.Composable
import com.rpfcoding.borutocharacterviewer.presentation.util.common.SearchWidget

@Composable
fun SearchTopBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    SearchWidget(
        text = text,
        onTextChange = onTextChange,
        onSearchClicked = onSearchClicked,
        onCloseClicked = onCloseClicked
    )
}
