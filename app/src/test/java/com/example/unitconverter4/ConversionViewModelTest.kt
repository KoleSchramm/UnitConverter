package com.example.unitconverter4

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ConversionViewModelTest {

    private lateinit var viewModel: ConversionViewModel
    private val converter = mockk<Converter>()

    @Before
    fun setup() {
        viewModel = ConversionViewModel(converter)
    }

    @Test
    fun `onInputValueChange updates result when input is valid`() {
        // Given
        val input = "10.0"
        val expectedResult = 25.4
        every { converter.convert(10.0, ConversionType.INCHES_TO_CM) } returns expectedResult

        // When
        viewModel.onInputValueChange(input)

        // Then
        assertEquals(input, viewModel.inputValue)
        assertEquals("25.40", viewModel.result)
        verify { converter.convert(10.0, ConversionType.INCHES_TO_CM) }
    }

    @Test
    fun `onInputValueChange clears result when input is invalid`() {
        // When
        viewModel.onInputValueChange("invalid")

        // Then
        assertEquals("invalid", viewModel.inputValue)
        assertEquals("", viewModel.result)
    }

    @Test
    fun `onTypeChange updates result`() {
        // Given
        every { converter.convert(10.0, ConversionType.INCHES_TO_CM) } returns 25.4
        every { converter.convert(10.0, ConversionType.CM_TO_INCHES) } returns 3.937
        
        viewModel.onInputValueChange("10.0")

        // When
        viewModel.onTypeChange(ConversionType.CM_TO_INCHES)

        // Then
        assertEquals(ConversionType.CM_TO_INCHES, viewModel.selectedType)
        assertEquals("3.94", viewModel.result)
    }
}