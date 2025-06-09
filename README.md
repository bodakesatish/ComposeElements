## Components Demonstrated:

1. **Simple Row:**
    * `Row` (Simple Row)`

```kotlin
// --- 1. Minimal/Default Code ---
@Composable
fun MinimalRowLayoutsDemo() {
   // Minimal Row
   Text("Minimal Row:", style = MaterialTheme.typography.titleMedium)
   Row(modifier = Modifier.border(1.dp, Color.Gray)) {
      DemoElement("Row 1", Color.LightGray)
      DemoElement("Row 2", Color.Green.copy(alpha = 0.5f))
      DemoElement("Row 3", Color.Blue.copy(alpha = 0.5f))
   }
   Text("Default: Arranges horizontally, children take their own height, aligned to Top.", fontSize = 12.sp, modifier = Modifier.padding(bottom = 12.dp))

   Spacer(modifier = Modifier.height(16.dp))
}
```

![Simple Row Demo](screenshots/row_simple_demo.png)

-----