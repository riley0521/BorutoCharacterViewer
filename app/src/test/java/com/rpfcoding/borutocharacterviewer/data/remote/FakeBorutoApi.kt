package com.rpfcoding.borutocharacterviewer.data.remote

import com.rpfcoding.borutocharacterviewer.data.remote.dto.ApiResponse
import com.rpfcoding.borutocharacterviewer.data.remote.dto.HeroDto
import com.rpfcoding.borutocharacterviewer.data.remote.dto.ShinobiRecordDto

class FakeBorutoApi : BorutoApi {

    private val heroes = listOf(
        HeroDto(
            id = 1,
            englishName = "Sasuke Uchiha",
            japaneseName = "うちはサスケ",
            image = "",
            about = "",
            status = "",
            gender = "",
            age = 33,
            month = "",
            day = "",
            abilities = listOf(),
            heightBasedOnAge = listOf(),
            species = listOf(),
            family = listOf(),
            shinobiRecord = ShinobiRecordDto()
        ),
        HeroDto(
            id = 2,
            englishName = "Naruto Uzumaki",
            japaneseName = "うずまきナルト",
            image = "",
            about = "",
            status = "",
            gender = "",
            age = 33,
            month = "",
            day = "",
            abilities = listOf(),
            heightBasedOnAge = listOf(),
            species = listOf(),
            family = listOf(),
            shinobiRecord = ShinobiRecordDto()
        ),
        HeroDto(
            id = 3,
            englishName = "Sakura Uchiha",
            japaneseName = "うちはサクラ",
            image = "",
            about = "",
            status = "",
            gender = "",
            age = 33,
            month = "",
            day = "",
            abilities = listOf(),
            heightBasedOnAge = listOf(),
            species = listOf(),
            family = listOf(),
            shinobiRecord = ShinobiRecordDto()
        )
    )

    override suspend fun getAllHeroes(page: Int): ApiResponse {
        return ApiResponse(
            success = false
        )
    }

    override suspend fun searchHeroes(name: String): ApiResponse {
        return ApiResponse(
            success = true,
            message = "Ok",
            heroes = findHeroes(name)
        )
    }

    private fun findHeroes(name: String): List<HeroDto> {
        val founded = mutableListOf<HeroDto>()

        return if (name.isNotEmpty()) {
            heroes.forEach { hero ->
                if (hero.englishName.lowercase().trim().contains(name.lowercase())) {
                    founded.add(hero)
                }
            }
            founded
        } else emptyList()
    }
}
