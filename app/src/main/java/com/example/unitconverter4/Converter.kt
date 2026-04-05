package com.example.unitconverter4

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Converter @Inject constructor() {
    fun convert(value: Double, type: ConversionType): Double {
        return when (type) {
            ConversionType.INCHES_TO_CM -> value * 2.54
            ConversionType.CM_TO_INCHES -> value / 2.54
            ConversionType.FAHRENHEIT_TO_CELSIUS -> (value - 32) * 5 / 9
            ConversionType.CELSIUS_TO_FAHRENHEIT -> (value * 9 / 5) + 32
        }
    }
}