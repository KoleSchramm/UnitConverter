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
        //If input is valid, result should be updated
        val input = "12.0"
        val expectedResult = 30.48
        every { converter.convert(12.0, ConversionType.INCHES_TO_CM) } returns expectedResult

        viewModel.onInputValueChange(input)

        assertEquals(input, viewModel.inputValue)
        assertEquals("30.48", viewModel.result)
        verify { converter.convert(12.0, ConversionType.INCHES_TO_CM) }
    }

    @Test
    fun `onInputValueChange clears result when input is invalid`() {
        //If input is invalid, result should be cleared
        viewModel.onInputValueChange("invalid")

        assertEquals("invalid", viewModel.inputValue)
        assertEquals("", viewModel.result)
    }

    @Test
    fun `onTypeChange updates result`() {
        //If type changes, result should be updated
        every { converter.convert(12.0, ConversionType.INCHES_TO_CM) } returns 30.48
        every { converter.convert(12.0, ConversionType.CM_TO_INCHES) } returns 4.72
        
        viewModel.onInputValueChange("12.0")

        viewModel.onTypeChange(ConversionType.CM_TO_INCHES)

        assertEquals(ConversionType.CM_TO_INCHES, viewModel.selectedType)
        assertEquals("4.72", viewModel.result)
    }
}