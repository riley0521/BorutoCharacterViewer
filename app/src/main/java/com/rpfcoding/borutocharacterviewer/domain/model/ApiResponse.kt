package com.rpfcoding.borutocharacterviewer.domain.model

import com.rpfcoding.borutocharacterviewer.data.remote.dto.HeroDto
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val heroes: List<HeroDto> = emptyList()
)
