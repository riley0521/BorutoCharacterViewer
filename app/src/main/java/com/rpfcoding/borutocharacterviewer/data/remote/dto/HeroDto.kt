package com.rpfcoding.borutocharacterviewer.data.remote.dto

import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import kotlinx.serialization.Serializable

@Serializable
data class HeroDto(
    val id: Int,
    val englishName: String,
    val japaneseName: String,
    val image: String,
    val about: String,
    val status: String,
    val gender: String,
    val age: Int,
    val month: String,
    val day: String,
    val abilities: List<String> = emptyList(),
    val heightBasedOnAge: List<String> = emptyList(),
    val species: List<String> = emptyList(),
    val family: List<String> = emptyList(),
    val shinobiRecord: ShinobiRecordDto = ShinobiRecordDto()
) {
    fun toHeroEntity(): HeroEntity {
        return HeroEntity(
            id = id,
            englishName = englishName,
            japaneseName = japaneseName,
            image = image,
            about = about,
            status = status,
            gender = gender,
            age = age,
            month = month,
            day = day,
            abilities = abilities,
            heightBasedOnAge = heightBasedOnAge,
            species = species,
            family = family,
            shinobiRecord = shinobiRecord
        )
    }
}
