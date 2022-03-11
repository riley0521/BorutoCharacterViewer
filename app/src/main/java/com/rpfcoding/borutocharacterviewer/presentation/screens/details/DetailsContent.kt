package com.rpfcoding.borutocharacterviewer.presentation.screens.details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rpfcoding.borutocharacterviewer.R
import com.rpfcoding.borutocharacterviewer.data.local.entity.HeroEntity
import com.rpfcoding.borutocharacterviewer.data.remote.dto.ShinobiRecordDto
import com.rpfcoding.borutocharacterviewer.presentation.theme.*
import com.rpfcoding.borutocharacterviewer.presentation.util.components.InfoBox
import com.rpfcoding.borutocharacterviewer.presentation.util.components.OrderedList

@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navController: NavController,
    selectedHero: HeroEntity?
) {

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = BOTTOM_SHEET_PEEK_HEIGHT,
        sheetContent = {
            selectedHero?.let { BottomSheetContent(selectedHero = it) }
        },
        content = {}
    )

}

@Composable
fun BottomSheetContent(
    selectedHero: HeroEntity,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
) {

    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(INFO_BOX_ICON_SIZE)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(id = R.string.app_logo),
                tint = contentColor
            )
            Text(
                modifier = Modifier
                    .weight(8f),
                text = "${selectedHero.englishName}\n(${selectedHero.japaneseName})",
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold,
                maxLines = 2
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.ic_cake),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.month} ${selectedHero.day}",
                smallText = stringResource(R.string.birthday),
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_calendar),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.age.toString(),
                smallText = stringResource(R.string.age),
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_bolt),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.gender,
                smallText = stringResource(R.string.gender),
                textColor = contentColor
            )
        }

        Text(
            modifier = Modifier
                .padding(bottom = SMALL_PADDING)
                .fillMaxWidth(),
            text = stringResource(R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .padding(bottom = MEDIUM_PADDING),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize
        )

        if(selectedHero.family.isNotEmpty()) {
            OrderedList(
                title = stringResource(R.string.family),
                items = selectedHero.family,
                textColor = contentColor
            )
            Spacer(modifier = Modifier.height(SMALL_PADDING))
        }

        Log.d("BottomSheet", selectedHero.heightBasedOnAge.toString())

        if(selectedHero.heightBasedOnAge.isNotEmpty()) {
            OrderedList(
                title = stringResource(R.string.height_based_on_age),
                items = selectedHero.heightBasedOnAge,
                textColor = contentColor
            )
            Spacer(modifier = Modifier.height(SMALL_PADDING))
        }

        if(selectedHero.abilities.isNotEmpty()) {
            OrderedList(
                title = stringResource(R.string.abilities),
                items = selectedHero.abilities,
                textColor = contentColor
            )
            Spacer(modifier = Modifier.height(SMALL_PADDING))
        }

    }

}


@Preview
@Composable
fun BottomSheetContentPreview() {
    BottomSheetContent(
        selectedHero = HeroEntity(
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
//                "150.8 cm - 153.2 cm (child)",
//                "168 cm (teenager)",
//                "182 cm (current)"
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
        )
    )
}