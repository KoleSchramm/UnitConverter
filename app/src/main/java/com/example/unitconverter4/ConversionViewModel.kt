package com.example.unitconverter4

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ConversionViewModel @Inject constructor(
    private val converter: Converter
) : ViewModel() {

    var inputValue by mutableStateOf("")
        private set

    var selectedType by mutableStateOf(ConversionType.INCHES_TO_CM)
        private set

    var result by mutableStateOf("")
        private set

    fun onInputValueChange(value: String) {
        inputValue = value
        performConversion()
    }

    fun onTypeChange(type: ConversionType) {
        selectedType = type
        performConversion()
    }

    private fun performConversion() {
        val input = inputValue.toDoubleOrNull()
        if (input != null) {
            val convertedValue = converter.convert(input, selectedType)
            result = String.format(Locale.US, "%.2f", convertedValue)
        } else {
            result = ""
        }
    }
}