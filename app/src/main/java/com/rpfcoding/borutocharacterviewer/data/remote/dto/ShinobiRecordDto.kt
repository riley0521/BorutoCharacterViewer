package com.rpfcoding.borutocharacterviewer.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ShinobiRecordDto(
    val rank: String = "N/A",
    val specialty: String = "N/A",
    val registrationNo: String = "N/A",
    val team: List<String> = emptyList()
)
