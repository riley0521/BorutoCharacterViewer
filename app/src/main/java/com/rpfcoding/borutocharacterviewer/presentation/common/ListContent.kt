package com.rpfcoding.borutocharacterviewer.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.rpfcoding.borutocharacterviewer.R
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import com.rpfcoding.borutocharacterviewer.data.remote.dto.ShinobiRecordDto
import com.rpfcoding.borutocharacterviewer.presentation.components.ShimmerEffect
import com.rpfcoding.borutocharacterviewer.presentation.navigation.Screen
import com.rpfcoding.borutocharacterviewer.ui.theme.*
import com.rpfcoding.borutocharacterviewer.util.Constants.BASE_URL

@ExperimentalCoilApi
@Composable
fun ListContent(
    navController: NavHostController,
    heroes: LazyPagingItems<HeroEntity>
) {

    val result = handlePagingResult(heroes = heroes)

    if(result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(space = SMALL_PADDING)
        ) {
            items(
                items = heroes,
                key = { hero ->
                    hero.id
                }
            ) { hero ->
                hero?.let {
                    HeroItem(
                        navController = navController,
                        hero = hero
                    )
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    heroes: LazyPagingItems<HeroEntity>
): Boolean {

    heroes.apply {

        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                EmptyScreen(error = error)
                false
            }
            else -> true
        }
    }

}

@ExperimentalCoilApi
@Composable
fun HeroItem(
    navController: NavHostController,
    hero: HeroEntity
) {
    
    val painter = rememberImagePainter(data = "$BASE_URL${hero.image}") {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }
    
    Box(
        modifier = Modifier
            .height(HERO_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.Details.passHeroId(heroId = hero.id))
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(id = R.string.hero_image),
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = "${hero.englishName} (${hero.japaneseName})",
                    color = MaterialTheme.colors.topAppBarContentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = hero.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
//                Row(
//                    modifier = Modifier
//                        .padding(top = SMALL_PADDING),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    RatingWidget(
//                        modifier = Modifier
//                            .padding(end = SMALL_PADDING),
//                        rating = hero.rating
//                    )
//                    Text(
//                        text = "(${hero.rating})",
//                        textAlign = TextAlign.Center,
//                        color = Color.White.copy(alpha = ContentAlpha.medium)
//                    )
//                }
            }
        }
    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun HeroItemPreview() {
    HeroItem(
        navController = rememberNavController(),
        hero = HeroEntity(
            id = 1,
            englishName = "Sasuke Uchiha",
            japaneseName = "うちはサスケ",
            image = "error",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            status = "Alive",
            gender = "Male",
            age = 35,
            month = "January",
            day = "5th",
            abilities = emptyList(),
            heightBasedOnAge = emptyList(),
            species = emptyList(),
            family = emptyList(),
            shinobiRecord = ShinobiRecordDto()
        )
    )
}

@ExperimentalCoilApi
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HeroItemDarkPreview() {
    HeroItem(
        navController = rememberNavController(),
        hero = HeroEntity(
            id = 1,
            englishName = "Sasuke Uchiha",
            japaneseName = "うちはサスケ",
            image = "error",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            status = "Alive",
            gender = "Male",
            age = 35,
            month = "January",
            day = "5th",
            abilities = emptyList(),
            heightBasedOnAge = emptyList(),
            species = emptyList(),
            family = emptyList(),
            shinobiRecord = ShinobiRecordDto()
        )
    )
}