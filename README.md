## Components Demonstrated:

1. **ExposedDropdownMenuDemo:** Text:**
    * `ExposedDropdownMenu` (ExposedDropdownMenu Text)`

```kotlin
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
```

![Standard Text Demo](screenshots/dropdown_demo.png)

-----