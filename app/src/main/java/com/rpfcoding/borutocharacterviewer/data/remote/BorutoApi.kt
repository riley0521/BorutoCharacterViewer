package com.rpfcoding.borutocharacterviewer.data.remote

import com.rpfcoding.borutocharacterviewer.data.remote.dto.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BorutoApi {

    @GET("api/v2/boruto/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("api/v2/boruto/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name: String
    ): ApiResponse
}
