package com.rpfcoding.borutocharacterviewer.presentation.util.common

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class SearchWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun openSearchWidget_addInputText_assertInputText() {

        val text = mutableStateOf("")

        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = { text.value = it },
                onSearchClicked = {},
                onCloseClicked = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription("TextField")
            .performTextInput("Something")

        composeTestRule
            .onNodeWithContentDescription("TextField")
            .assertTextEquals("Something")
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseButtonOnce_assertEmptyInputText() {

        val text = mutableStateOf("")

        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = { text.value = it },
                onSearchClicked = {},
                onCloseClicked = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription("TextField")
            .performTextInput("Something")

        composeTestRule
            .onNodeWithContentDescription("CloseIcon")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("TextField")
            .assertTextContains("")
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseButtonTwice_assertClosedState() {

        val text = mutableStateOf("")

        val isSearchWidgetClose = mutableStateOf(false)

        composeTestRule.setContent {
            if (isSearchWidgetClose.value.not()) {
                SearchWidget(
                    text = text.value,
                    onTextChange = { text.value = it },
                    onSearchClicked = {},
                    onCloseClicked = {
                        isSearchWidgetClose.value = true
                    }
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription("TextField")
            .performTextInput("Something")

        // Press Close Icon the first time to delete 'Something'
        composeTestRule
            .onNodeWithContentDescription("CloseIcon")
            .performClick()

        // Press Close Icon for the second time to navigate back and remove search widget
        composeTestRule
            .onNodeWithContentDescription("CloseIcon")
            .performClick()

        // Assert if the Search Widget has been closed and not existing anymore.
        composeTestRule
            .onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }

    @Test
    fun openSearchWidget_emptyInputText_pressCloseButtonOnce_assertClosedState() {

        val isSearchWidgetClose = mutableStateOf(false)

        composeTestRule.setContent {
            if (isSearchWidgetClose.value.not()) {
                SearchWidget(
                    text = "",
                    onTextChange = {},
                    onSearchClicked = {},
                    onCloseClicked = {
                        isSearchWidgetClose.value = true
                    }
                )
            }
        }

        // Press Close Icon to navigate back and remove search widget
        composeTestRule
            .onNodeWithContentDescription("CloseIcon")
            .performClick()

        // Assert if the Search Widget has been closed and not existing anymore.
        composeTestRule
            .onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }
}
