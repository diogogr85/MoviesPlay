package com.diogogr85.moviesplay.ui.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class PopularScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPopularScreen() {
        composeTestRule.setContent {
            PopularScreen() {}
        }

        composeTestRule
            .onNodeWithText(POPULAR_SCREEN_TITLE)
            .assertIsDisplayed()
    }

    private companion object {
        const val POPULAR_SCREEN_TITLE = "Filmes Populares"
    }
}