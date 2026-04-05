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

    //Test in to cm
    @Test
    fun testConversionInchesToCm() {
        composeTestRule.onNodeWithTag("input_field").performTextInput("12")
        composeTestRule.onNodeWithTag("result_text").assertTextContains("30.48", substring = true)
    }

    //Test cm to in
    @Test
    fun testSwitchConversionType() {
        composeTestRule.onNodeWithTag("conversion_dropdown").performClick()
        composeTestRule.onNodeWithTag("dropdown_item_CM_TO_INCHES").performClick()
        composeTestRule.onNodeWithTag("input_field").performTextInput("30.48")
        composeTestRule.onNodeWithTag("result_text").assertTextContains("12.00", substring = true)
    }
}