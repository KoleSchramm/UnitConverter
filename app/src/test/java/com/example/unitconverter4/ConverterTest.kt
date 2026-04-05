package com.example.unitconverter4

import org.junit.Assert.assertEquals
import org.junit.Test

class ConverterTest {

    private val converter = Converter()

    @Test
    fun testInchesToCm() {
        val result = converter.convert(10.0, ConversionType.INCHES_TO_CM)
        assertEquals(25.4, result, 0.01)
    }

    @Test
    fun testCmToInches() {
        val result = converter.convert(25.4, ConversionType.CM_TO_INCHES)
        assertEquals(10.0, result, 0.01)
    }

    @Test
    fun testFahrenheitToCelsius() {
        val result = converter.convert(32.0, ConversionType.FAHRENHEIT_TO_CELSIUS)
        assertEquals(0.0, result, 0.01)
    }

    @Test
    fun testCelsiusToFahrenheit() {
        val result = converter.convert(0.0, ConversionType.CELSIUS_TO_FAHRENHEIT)
        assertEquals(32.0, result, 0.01)
    }
}