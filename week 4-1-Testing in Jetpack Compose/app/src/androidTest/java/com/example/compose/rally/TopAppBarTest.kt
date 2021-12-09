package com.example.compose.rally

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.ui.components.RallyTopAppBar
import kotlinx.coroutines.*
import org.junit.Rule
import org.junit.Test
import kotlin.coroutines.coroutineContext

class TopAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest() {
        val allScreens = RallyScreen.values().toList()

        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = {},
                currentScreen = RallyScreen.Accounts
            )
        }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertIsSelected()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = {},
                currentScreen = RallyScreen.Accounts
            )
        }

        composeTestRule.onNode(
            useUnmergedTree = true,
            matcher = hasText(RallyScreen.Accounts.name.uppercase()) and
                    hasParent(hasContentDescription(RallyScreen.Accounts.name))
        ).printToLog("currentLabelExists")

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertExists()
    }

    @Test
    fun rallyTopAppBarTest_changeSelection() {
        val allScreens = RallyScreen.values().toList()

        composeTestRule.setContent {
            val currentScreen by rememberSaveable { mutableStateOf(RallyScreen.Overview) }

            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = {},
                currentScreen = currentScreen
            )
        }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .performClick()
            .assertContentDescriptionEquals(RallyScreen.Accounts.name)
            .printToLog("TEST!")
    }
}