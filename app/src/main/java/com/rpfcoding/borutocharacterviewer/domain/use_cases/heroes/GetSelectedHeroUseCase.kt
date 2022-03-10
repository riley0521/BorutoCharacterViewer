package com.rpfcoding.borutocharacterviewer.domain.use_cases.heroes

import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import com.rpfcoding.borutocharacterviewer.domain.repository.LocalHeroRepository

class GetSelectedHeroUseCase(
    private val repository: LocalHeroRepository
) {

    operator fun invoke(heroId: Int): HeroEntity {
        return repository.getSelectedHero(heroId = heroId)
    }

}