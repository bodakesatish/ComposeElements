## Components Demonstrated:

1. **AlertDialog Demo:**
    * `AlertDialog` (AlertDialog Demo)`

```kotlin
@Composable
fun DialogDemo() {
   val context = LocalContext.current
   // --- State for AlertDialog ---
   // `showAlertDialog` controls the visibility of the AlertDialog.
   var showAlertDialog by remember { mutableStateOf(false) }

   // --- AlertDialog Section ---
   Text("AlertDialog Demo", style = MaterialTheme.typography.titleMedium)
   Button(onClick = { showAlertDialog = true }) {
      Text("Show Alert Dialog")
   }

   // --- AlertDialog Composable ---
   if (showAlertDialog) {
      // `AlertDialog` is a standard Material Design dialog.
      // It's modal and typically used for critical information or decisions.
      AlertDialog(
         onDismissRequest = {
            // Called when the user tries to dismiss the dialog by clicking outside
            // or pressing the back button. Setting `showAlertDialog` to false closes it.
            showAlertDialog = false
            Toast.makeText(context, "Alert Dismissed", Toast.LENGTH_SHORT).show()
         },
         // `icon` Composable for an optional icon at the top of the dialog.
         icon = { Icon(Icons.Filled.Warning, contentDescription = "Warning Icon") },
         // `title` Composable for the dialog's title.
         title = {
            Text(text = "Important Message", fontWeight = FontWeight.Bold)
         },
         // `text` Composable for the main body content of the dialog.
         text = {
            Text("This is an example of an AlertDialog in Jetpack Compose. Please choose an action.")
         },
         // `confirmButton` Composable for the primary action button.
         confirmButton = {
            TextButton(
               onClick = {
                  showAlertDialog = false
                  Toast.makeText(context, "Confirmed!", Toast.LENGTH_SHORT).show()
                  // Perform confirm action here
               }
            ) {
               Text("Confirm")
            }
         },
         // `dismissButton` Composable for an optional secondary action button.
         dismissButton = {
            TextButton(
               onClick = {
                  showAlertDialog = false
                  Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
                  // Perform dismiss action here
               }
            ) {
               Text("Cancel")
            }
         }
         // Other parameters like `shape`, `containerColor`, `tonalElevation` can be used for customization.
      )
   }
}
```

![AlertDialog Demo](screenshots/alert_dialog_demo.png)

-----