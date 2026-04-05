package com.example.unitconverter4

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ConversionUiTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testConversionInchesToCm() {
        // Find input field and enter value
        composeTestRule.onNodeWithTag("input_field").performTextInput("10")

        // Result should be visible (10 * 2.54 = 25.40)
        composeTestRule.onNodeWithTag("result_text").assertTextContains("25.40", substring = true)
    }

    @Test
    fun testSwitchConversionType() {
        // Open dropdown
        composeTestRule.onNodeWithTag("conversion_dropdown").performClick()

        // Select CM to Inches - wait for item to appear
        composeTestRule.onNodeWithTag("dropdown_item_CM_TO_INCHES").performClick()

        // Enter value
        composeTestRule.onNodeWithTag("input_field").performTextInput("25.4")

        // Check result (25.4 / 2.54 = 10.00)
        composeTestRule.onNodeWithTag("result_text").assertTextContains("10.00", substring = true)
    }
}