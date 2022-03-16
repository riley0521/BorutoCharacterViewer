package com.rpfcoding.borutocharacterviewer.presentation.util.common

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import com.rpfcoding.borutocharacterviewer.ui.theme.SMALL_PADDING
import org.junit.Rule
import org.junit.Test

class RatingWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun passZeroPointZeroRating_assertFiveEmptyStars() {

        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 0.0
            )
        }

        composeTestRule
            .onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)

        composeTestRule
            .onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)

        composeTestRule
            .onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(5)
    }

    @Test
    fun passZeroPointFiveRating_assert_oneHalfFilledStar_fourEmptyStars() {

        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 0.6
            )
        }

        composeTestRule
            .onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(1)

        composeTestRule
            .onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)

        composeTestRule
            .onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(4)
    }

    @Test
    fun passZeroPointSixRating_assert_oneFilledStar_fourEmptyStars() {

        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 0.5
            )
        }

        composeTestRule
            .onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)

        composeTestRule
            .onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(1)

        composeTestRule
            .onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(4)
    }

    @Test
    fun passFivePointZeroRating_assertFiveFilledStars() {

        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 5.0
            )
        }

        composeTestRule
            .onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(5)

        composeTestRule
            .onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)

        composeTestRule
            .onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(0)
    }

    @Test
    fun passFivePointOneRating_expectError_assertFiveEmptyStars() {

        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 5.1
            )
        }

        composeTestRule
            .onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)

        composeTestRule
            .onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)

        composeTestRule
            .onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(5)
    }

    @Test
    fun passSixPointZeroRating_expectError_assertFiveEmptyStars() {

        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 6.0
            )
        }

        composeTestRule
            .onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)

        composeTestRule
            .onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)

        composeTestRule
            .onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(5)
    }

    @Test
    fun passNegativeRating_expectError_assertFiveEmptyStars() {

        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = -1.0
            )
        }

        composeTestRule
            .onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)

        composeTestRule
            .onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)

        composeTestRule
            .onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(5)
    }

    @Test
    fun passTwoPointFiveRating_assert_twoFilledStar_oneHalfFilledStar_twoEmptyStar() {

        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 2.5
            )
        }

        composeTestRule
            .onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(2)

        composeTestRule
            .onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(1)

        composeTestRule
            .onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(2)
    }
}
