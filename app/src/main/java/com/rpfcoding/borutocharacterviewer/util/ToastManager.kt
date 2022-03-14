package com.rpfcoding.borutocharacterviewer.util

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ToastManager(
    text: String
) {
    Toast.makeText(
        LocalContext.current,
        text,
        Toast.LENGTH_SHORT
    ).show()
}
