package com.example.unitconverter4

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversionScreen(
    viewModel: ConversionViewModel = viewModel()
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //Title
        Text(
            text = "Converter",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        //Dropdown Menu
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = viewModel.selectedType.label,
                onValueChange = {},
                readOnly = true,
                label = { Text("Select Conversion") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth()
                    .testTag("conversion_dropdown")
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                ConversionType.entries.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type.label) },
                        onClick = {
                            viewModel.onTypeChange(type)
                            expanded = false
                        },
                        modifier = Modifier.testTag("dropdown_item_${type.name}")
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Text field
        OutlinedTextField(
            value = viewModel.inputValue,
            onValueChange = { viewModel.onInputValueChange(it) },
            label = { Text("Value") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("input_field")
        )

        Spacer(modifier = Modifier.height(24.dp))

        //Output
        Text(
            text = if (viewModel.result.isNotEmpty()) "Result: ${viewModel.result}" else "",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.testTag("result_text")
        )
    }
}