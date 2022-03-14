package com.rpfcoding.borutocharacterviewer.data.remote

import com.rpfcoding.borutocharacterviewer.data.remote.HeroPages.page2
import com.rpfcoding.borutocharacterviewer.data.remote.HeroPages.page3
import com.rpfcoding.borutocharacterviewer.data.remote.HeroPages.page4
import com.rpfcoding.borutocharacterviewer.data.remote.HeroPages.page5
import com.rpfcoding.borutocharacterviewer.data.remote.HeroPages.page6
import com.rpfcoding.borutocharacterviewer.data.remote.dto.ApiResponse
import com.rpfcoding.borutocharacterviewer.data.remote.dto.HeroDto
import com.rpfcoding.borutocharacterviewer.data.remote.dto.ShinobiRecordDto
import java.io.IOException

class FakeBorutoApi2 : BorutoApi {

    private val heroes: Map<Int, List<HeroDto>> by lazy {
        mapOf(
            1 to page1,
            2 to page2,
            3 to page3,
            4 to page4,
            5 to page5,
            6 to page6
        )
    }

    private var page1 = listOf(
        HeroDto(
            id = 1,
            englishName = "Sasuke Uchiha",
            japaneseName = "うちはサスケ",
            image = "/images/sasuke.jpg",
            about = "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
            status = "Alive",
            gender = "Male",
            age = 33,
            month = "July",
            day = "23rd",
            abilities = listOf(
                "Sharingan",
                "Eternal Mangekyo Sharingan",
                "Rinnegan",
                "Sussano",
                "Amaterasu"
            ),
            heightBasedOnAge = listOf(
                "150.8 cm - 153.2 cm (child)",
                "168 cm (teenager)",
                "182 cm (current)"
            ),
            species = listOf(
                "Human"
            ),
            family = listOf(
                "Fugaku Uchiha (Father)",
                "Mikoto Uchiha (Mother)",
                "Itachi Uchiha (Older Brother)",
                "Sarada Uchiha (Daughter)",
                "Sakura Uchiha (Wife)"
            ),
            shinobiRecord = ShinobiRecordDto(
                rank = "1",
                specialty = "Ninjutsu",
                registrationNo = "012606",
                team = listOf(
                    "Team 7"
                )
            )
        ),
        HeroDto(
            id = 2,
            englishName = "Naruto Uzumaki",
            japaneseName = "うずまきナルト",
            image = "/images/naruto.jpg",
            about = "Naruto Uzumaki (うずまきナルト, Uzumaki Naruto) is a shinobi of Konohagakure's Uzumaki clan. He became the jinchūriki of the Nine-Tails on the day of his birth — a fate that caused him to be shunned by most of Konoha throughout his childhood. After joining Team Kakashi, Naruto worked hard to gain the village's acknowledgement all the while chasing his dream to become Hokage.",
            status = "Alive",
            gender = "Male",
            age = 33,
            month = "October",
            day = "10th",
            abilities = listOf(
                "Rasengan",
                "Rasen-Shuriken",
                "Shadown Clone",
                "Senin Mode",
                "Lava Release",
                "Magnet Release",
                "Boil Release"
            ),
            heightBasedOnAge = listOf(
                "180 cm"
            ),
            species = listOf(
                "Human"
            ),
            family = listOf(
                "Minato Namikaze (Father)",
                "Kushina Uzumaki (Mother)",
                "Hinata Uzumaki (Wife)",
                "Boruto Uzumaki (Son)",
                "Himawari Uzumaki (Daughter)",
                "Jiraiya (Godfather)",
                "Kawaki (Foster Son)",
                "Hiashi Hyūga (Father-in-law)",
                "Hanabi Hyūga (Sister-in-law)",
                "Neji Hyuga (Cousin-in-law)"
            ),
            shinobiRecord = ShinobiRecordDto(
                rank = "Kage",
                specialty = "Rasengan",
                registrationNo = "012607",
                team = listOf(
                    "Team 7",
                    "Sasuke Recovery Team",
                    "Kazekage Recuse Team",
                    "Eight Man Squad",
                    "Hanabi Rescue Team"
                )
            )
        ),
        HeroDto(
            id = 3,
            englishName = "Sakura Uchiha",
            japaneseName = "うちはサクラ",
            image = "/images/sakura.jpg",
            about = "Sakura Uchiha (うちはサクラ, Uchiha Sakura, née Haruno (春野)) is a kunoichi of Konohagakure. When assigned to Team 7, Sakura quickly finds herself ill-prepared for the duties of a shinobi. However, after training under the Sannin Tsunade, she overcomes this, and becomes recognised as one of the greatest medical-nin in the world.",
            status = "Alive",
            gender = "Female",
            age = 33,
            month = "March",
            day = "28th",
            abilities = listOf(
                "Chakra Control",
                "Medical Ninjutsu",
                "Super Strength"
            ),
            heightBasedOnAge = listOf(
                "165 cm"
            ),
            species = listOf(
                "Human"
            ),
            family = listOf(
                "Kizashi",
                "Mebuki",
                "Sarada Uchiha (Daughter)",
                "Sasuke Uchiha (Husband)"
            ),
            shinobiRecord = ShinobiRecordDto(
                rank = "Jonin",
                specialty = "Medical Ninjutsu",
                registrationNo = "012601",
                team = listOf(
                    "Team 7",
                    "Kazekage Rescue Team",
                    "Eight Man Squad",
                    "Hanabi Rescue Team (Novel)",
                    "Ino-Saku-Sai (Novel)",
                    "Third Division",
                    "Medic Corps",
                    "Criptanalysis Team",
                    "Hokage Guard Platoon (Novel)"
                )
            )
        ),
        HeroDto(
            id = 4,
            englishName = "Boruto Uzumaki",
            japaneseName = "うずまきボルト",
            image = "/images/boruto.png",
            about = "Boruto Uzumaki (うずまきボルト, Uzumaki Boruto) is a shinobi from Konohagakure's Uzumaki clan and a direct descendant of the Hyūga clan through his mother. While initially resentful of his father and his absence since becoming Hokage, Boruto eventually comes to respect his father and duties.",
            status = "Alive",
            gender = "Male",
            age = 16,
            month = "March",
            day = "27th",
            abilities = listOf(
                "Jogan",
                "Karma",
                "Rasengan"
            ),
            heightBasedOnAge = listOf(
                "110 cm (Blank Period)",
                "145 cm (Movie)",
                "148 cm (Boruto Series)",
                "163 cm (Flash Forward)"
            ),
            species = listOf(
                "Human (Formerly)",
                "Otsutsuki (Current)"
            ),
            family = listOf(
                "Naruto Uzumaki (Father)",
                "Hinata Uzumaki (Mother)",
                "Himawari Uzumaki (Younger Sister)",
                "Kawaki (Adoptive Brother)",
                "Minato Namikaze (Grand Father)",
                "Kushina Uzumaki (Grand Mother)",
                "Hiashi Hyūga (Grand Father)",
                "Hanabi Hyūga (Aunt)",
                "Hizashi Hyūga (Grand Uncle)",
                "Neji Hyuga (Cousin)"
            ),
            shinobiRecord = ShinobiRecordDto(
                rank = "Genin",
                specialty = "Vanished Rasengan",
                registrationNo = "N/A",
                team = listOf(
                    "Team 7"
                )
            )
        ),
        HeroDto(
            id = 5,
            englishName = "Sarada Uchiha",
            japaneseName = "うちはサラダ",
            image = "/images/sarada.jpg",
            about = "Sarada Uchiha (うちはサラダ, Uchiha Sarada) is a kunoichi from Konohagakure's Uchiha clan. Because she was raised only by her mother without having her father around, Sarada initially struggles to understand who she is or what she's supposed to be. After meeting him with the help of Naruto Uzumaki, Sarada comes to believe that she is defined by the connections she has with others, and as a member of Team Konohamaru, she seeks to someday become Hokage so that she can connect with as many people as possible.",
            status = "Alive",
            gender = "Female",
            age = 12,
            month = "March",
            day = "31st",
            abilities = listOf(
                "Sharingan"
            ),
            heightBasedOnAge = listOf(
                "125 cm (Blank Period)",
                "147 cm (Boruto Movie)"
            ),
            species = listOf(
                "Human"
            ),
            family = listOf(
                "Sasuke Uchiha (Father)",
                "Sakura Uchiha (Mother)",
                "Itachi Uchiha (Uncle)",
                "Mikoto Uchiha (Grand Mother)",
                "Fugaku Uchiha (Grand Father)",
                "Mebuki Haruno (Grand Mother)",
                "Kizashi Haruno (Grand Father)"
            ),
            shinobiRecord = ShinobiRecordDto(
                rank = "Chunin",
                specialty = "N/A",
                registrationNo = "N/A",
                team = listOf(
                    "Team 7"
                )
            )
        ),
    )

    fun clearData() {
        page1 = emptyList()
    }

    private var exception = false

    fun addException() {
        exception = true
    }

    override suspend fun getAllHeroes(page: Int): ApiResponse {

        if (exception) {
            throw IOException()
        }

        require(page in 1..6)
        return ApiResponse(
            success = false,
            message = "Ok",
            prevPage = calculatePage(page)["prevPage"],
            nextPage = calculatePage(page)["nextPage"],
            heroes = heroes[page]!!,
            lastUpdated = System.currentTimeMillis()
        )
    }

    private fun calculatePage(page: Int): Map<String, Int?> {

        if (page1.isEmpty()) {
            return mapOf(
                "prevPage" to null,
                "nextPage" to null
            )
        }

        var prevPage: Int? = page
        var nextPage: Int? = page

        if (page in 1..5) {
            nextPage = nextPage?.plus(1)
        }
        if (page in 2..6) {
            prevPage = prevPage?.minus(1)
        }
        if (page == 1) {
            prevPage = null
        }
        if (page == 6) {
            nextPage = null
        }

        return mapOf(
            "prevPage" to prevPage,
            "nextPage" to nextPage
        )
    }

    override suspend fun searchHeroes(name: String): ApiResponse {
        return ApiResponse(
            success = false
        )
    }
}
