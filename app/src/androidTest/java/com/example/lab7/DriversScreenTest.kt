package com.example.lab_7

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.lab7.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DriversScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>() // access MainActivity

    // helper: go from the home screen to the drivers screen
    private fun goToDriversScreen() {
        composeTestRule
            .onNodeWithTag("driversButton")
            .performClick()
    }

    // helper: type a driver name and click add
    private fun addDriver(name: String) {
        composeTestRule
            .onNodeWithTag("driverInput")
            .performTextInput(name)

        composeTestRule
            .onNodeWithTag("addDriverButton")
            .performClick()
    }

    @Test
    fun navigate_from_home_to_drivers_screen() {
        // verify we start on the home screen
        composeTestRule
            .onNodeWithText("FormulaZero Home")
            .assertExists()

        goToDriversScreen()

        // verify drivers screen
        composeTestRule
            .onNodeWithText("FormulaZero Drivers")
            .assertIsDisplayed()
    }

    @Test
    fun add_driver_and_verify_displayed() {
        goToDriversScreen()

        addDriver("Keno")

        // verify driver appears in the list
        composeTestRule
            .onNodeWithText("Keno")
            .assertIsDisplayed()
    }

    @Test
    fun clear_drivers_removes_all_from_list() {
        goToDriversScreen()

        addDriver("Keno")
        addDriver("you")

        // verify both drivers appear
        composeTestRule.onNodeWithText("Keno").assertExists()
        composeTestRule.onNodeWithText("you").assertExists()

        // clear all drivers
        composeTestRule
            .onNodeWithTag("clearDriversButton")
            .performClick()

        // verify drivers are gone
        composeTestRule.onNodeWithText("Keno").assertDoesNotExist()
        composeTestRule.onNodeWithText("you").assertDoesNotExist()
    }

    @Test
    fun back_button_returns_to_home_screen() {
        goToDriversScreen()

        composeTestRule
            .onNodeWithTag("backButton")
            .performClick()

        // verify return to home screen
        composeTestRule
            .onNodeWithText("FormulaZero Home")
            .assertIsDisplayed()
    }
}