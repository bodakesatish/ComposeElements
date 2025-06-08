## Components Demonstrated:

1. **ElevatedCard:**
    * `ElevatedCard` (ElevatedCard)`

```kotlin

@Composable
fun ElevatedCardDemo(context: android.content.Context) {
   ElevatedCard(
      // `onClick` can be added to make the entire card clickable.
      onClick = { Toast.makeText(context, "Elevated Card clicked!", Toast.LENGTH_SHORT).show() },
      modifier = Modifier
         .fillMaxWidth(),
      elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
   ) {
      Column(
         modifier = Modifier
            .padding(16.dp) // Padding inside the card
            .fillMaxWidth()
      ) {
         Text(
            text = "Item Title",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
         )
         Spacer(modifier = Modifier.height(4.dp))
         Text(
            text = "This is some supporting text for the item. It can span multiple lines and provide more details.",
            style = MaterialTheme.typography.bodyMedium
         )
         Spacer(modifier = Modifier.height(12.dp))
         // Example of an image placeholder within a card.
         ImagePlaceholder()
         Spacer(modifier = Modifier.height(12.dp))
         Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
         ) {
            TextButton(onClick = { Toast.makeText(context, "Action 1", Toast.LENGTH_SHORT).show() }) {
               Text("ACTION 1")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { Toast.makeText(context, "Action 2", Toast.LENGTH_SHORT).show() }) {
               Text("ACTION 2")
            }
         }
      }
   }
}
// Simple placeholder for an image within a card.
@Composable
fun ImagePlaceholder(modifier: Modifier = Modifier) {
   Box(
      modifier = modifier
         .fillMaxWidth()
         .height(150.dp)
         .clip(RoundedCornerShape(8.dp)) // Rounded corners for the placeholder
         .background(MaterialTheme.colorScheme.secondaryContainer),
      contentAlignment = Alignment.Center
   ) {
      Image(
         painter = painterResource(id = R.drawable.ic_home_144),
         contentDescription = "Descriptive text",
         contentScale = ContentScale.FillHeight,
         modifier = Modifier.fillMaxSize()
      )
   }
}
```

![ElevatedCard Demo](screenshots/elevated_card_demo.png)

-----

2. **HorizontalDivider:**
   * `HorizontalDivider` (HorizontalDivider)`

```kotlin
@Composable
fun HorizontalDividerDemo() {
    Text("HorizontalDivider", style = MaterialTheme.typography.titleMedium)
    Spacer(modifier = Modifier.height(8.dp))
    Column {
        Text("Content above divider")
        Spacer(modifier = Modifier.height(8.dp))
        // `HorizontalDivider` is used to separate content horizontally.
        // It's the standard Material 3 divider for this purpose.
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(), // Typically spans the full width
            thickness = 1.dp, // Default thickness
            color = MaterialTheme.colorScheme.outlineVariant // Default color
        )
        // `HorizontalDivider(thickness = Dp.Hairline)` can be used for a very thin line.
        Spacer(modifier = Modifier.height(8.dp))
        Text("Content below divider")
    }
}
```

![HorizontalDivider Demo](screenshots/horizontal_divider_demo.png)

-----