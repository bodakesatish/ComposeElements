package com.bodakesatish.composecomponents.textfield

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// --- Basic TextField and OutlinedTextField ---
@Composable
fun BasicTextFieldsDemo() {

    // State for the basic TextField. `rememberSaveable` retains state across configuration changes (e.g., rotation).
    var textState1 by rememberSaveable { mutableStateOf("") }
    // Standard TextField (filled style by default in Material 3).
    TextField(
        value = textState1, // The current text to display.
        onValueChange = { newText -> textState1 = newText }, // Lambda called when the user inputs text.
        label = { Text("Standard TextField") }, // Label that floats above the field when focused or non-empty.
        placeholder = { Text("Enter text here") }, // Hint text shown when the field is empty and not focused.
        modifier = Modifier.fillMaxWidth() // Makes the TextField take the full available width.
    )

    var textState2 by rememberSaveable { mutableStateOf("") }
    // OutlinedTextField has a border around it.
    OutlinedTextField(
        value = textState2,
        onValueChange = { textState2 = it }, // `it` is the new text value.
        label = { Text("Outlined TextField") },
        placeholder = { Text("Type something...") },
        modifier = Modifier.fillMaxWidth()
    )
}

// --- TextFields with Leading and Trailing Icons ---
@Composable
fun TextFieldsWithIconsDemo() {
    var emailText by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = emailText,
        onValueChange = { emailText = it },
        label = { Text("Email Address") },
        placeholder = { Text("your.email@example.com") },
        leadingIcon = { // Icon displayed at the start (left in LTR) of the TextField.
            Icon(Icons.Filled.Email, contentDescription = "Email Icon")
        },
        trailingIcon = { // Icon displayed at the end (right in LTR) of the TextField.
            // Example: Clear button for the text field
            if (emailText.isNotEmpty()) {
                IconButton(onClick = { emailText = "" /* Clear the text */ }) {
                    Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
                }
            }
        },
        // Add keyboardOptions here
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email // This tells the system to show an email-friendly keyboard
        ),
        singleLine = true, // Usually, email addresses are single-line
        modifier = Modifier.fillMaxWidth()
    )

    var searchText by rememberSaveable { mutableStateOf("") }
    TextField(
        value = searchText,
        onValueChange = { searchText = it },
        label = { Text("Search") },
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "Search Icon")
        },
        singleLine = true, // Often used with search fields.
        modifier = Modifier.fillMaxWidth()
    )
}

// --- TextField States (Error, Disabled, ReadOnly) and Supporting Text ---
@Composable
fun TextFieldStatesDemo() {
    val context = LocalContext.current // For Toast

    var errorInput by rememberSaveable { mutableStateOf("") }
    val isError = errorInput.length < 5 && errorInput.isNotEmpty() // Example error condition

    OutlinedTextField(
        value = errorInput,
        onValueChange = { errorInput = it },
        label = { Text("Input with Error") },
        isError = isError, // If true, the TextField is displayed in an error state (e.g., red label and border).
        supportingText = { // Text displayed below the TextField, often for instructions or error messages.
            if (isError) {
                Text(
                    text = "Input must be at least 5 characters",
                    color = MaterialTheme.colorScheme.error // Use error color for error messages.
                )
            } else {
                Text("Please enter at least 5 characters.")
            }
        },
        trailingIcon = {
            if (isError) Icon(Icons.Filled.Error, "Error Icon", tint = MaterialTheme.colorScheme.error)
        },
        modifier = Modifier.fillMaxWidth()
    )

    var disabledText by rememberSaveable { mutableStateOf("You can't change me") }
    TextField(
        value = disabledText,
        onValueChange = { /* This won't be called */ },
        label = { Text("Disabled TextField") },
        enabled = false, // If false, the TextField is visually disabled and non-interactive.
        modifier = Modifier.fillMaxWidth()
    )

    var readOnlyText by rememberSaveable { mutableStateOf("You can read, but not write.") }
    TextField(
        value = readOnlyText,
        onValueChange = { /* This won't be called if readOnly is true */ },
        label = { Text("Read-Only TextField") },
        readOnly = true, // If true, the user cannot edit the text, but can still focus and select/copy it.
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = { Toast.makeText(context, "Copied (not really!)", Toast.LENGTH_SHORT).show() }) {
                Text("COPY") // Example action for a read-only field
            }
        }
    )

    var multiLineText by rememberSaveable { mutableStateOf("This is\na multiline\nTextField.") }
    OutlinedTextField(
        value = multiLineText,
        onValueChange = { multiLineText = it },
        label = { Text("Multiline TextField") },
        maxLines = 3, // Specifies the maximum number of lines the TextField can expand to.
        // If `singleLine` is false (default), it allows multiple lines.
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp) // Ensure it has some min height
    )
}

// --- Keyboard Options and Actions ---
@Composable
fun KeyboardCustomizationDemo() {
    val focusManager = LocalFocusManager.current // Used to control focus (e.g., move to next field).
    val context = LocalContext.current

    var numberInput by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = numberInput,
        onValueChange = { if (it.all { char -> char.isDigit() }) numberInput = it }, // Allow only digits
        label = { Text("Number Input (Digits Only)") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number, // Specifies the type of keyboard to show (e.g., numeric).
            imeAction = ImeAction.Next // Specifies the action button on the keyboard (e.g., "Next", "Done", "Search").
        ),
        keyboardActions = KeyboardActions( // Defines actions to perform when an IME action is pressed.
            onNext = { focusManager.moveFocus(FocusDirection.Down) } // Moves focus to the next focusable item downwards.
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )

    var sentenceInput by rememberSaveable { mutableStateOf("") }
    TextField(
        value = sentenceInput,
        onValueChange = { sentenceInput = it },
        label = { Text("Sentence Input (Auto Capitalize)") },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences, // Automatically capitalizes the first letter of each sentence.
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus() // Clears focus from the current field, often hiding the keyboard.
                Toast.makeText(context, "Input Done: $sentenceInput", Toast.LENGTH_SHORT).show()
                Log.d("InputFields", "Done clicked: $sentenceInput")
            }
        ),
        modifier = Modifier.fillMaxWidth()
    )
}