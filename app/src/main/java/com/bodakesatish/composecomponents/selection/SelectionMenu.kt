package com.bodakesatish.composecomponents.selection

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuDemo(context: android.content.Context) {
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Apple", "Banana", "Cherry", "Date", "Elderberry")
    var selectedText by remember { mutableStateOf(suggestions[0]) }

    Text("Exposed Dropdown Menu (Spinner Style):", style = MaterialTheme.typography.titleMedium)
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { /* Usually read-only or type to filter */ },
            readOnly = true,
            label = { Text("Fruit") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor() // This is important!
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label) },
                    onClick = {
                        selectedText = label
                        expanded = false
                        Toast.makeText(context, "Selected: $label", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}