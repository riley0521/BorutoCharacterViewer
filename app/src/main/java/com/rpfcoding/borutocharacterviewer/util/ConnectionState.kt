package com.rpfcoding.borutocharacterviewer.util

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}
