package com.rpfcoding.borutocharacterviewer.domain.use_cases.heroes

import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import com.rpfcoding.borutocharacterviewer.domain.repository.LocalHeroRepository

class SaveSearchedHeroesUseCase(
    private val repository: LocalHeroRepository
) {

    suspend operator fun invoke(heroes: List<HeroEntity>) {
        repository.addHeroes(heroes = heroes)
    }
}
