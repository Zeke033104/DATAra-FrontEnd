package com.capstone.datara.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.capstone.datara.ui.theme.DarkBackground
import com.capstone.datara.ui.theme.DarkSurface
import com.capstone.datara.ui.theme.PrimaryBlue
import com.capstone.datara.ui.theme.PrimaryGreen

/**
 * A reusable dialog for editing a single profile field (Name, Email, Address).
 * Front-end only — saves locally to the passed [currentValue] via [onSave].
 *
 * @param fieldLabel   e.g. "NAME", "EMAIL", "ADDRESS"
 * @param currentValue The current field value pre-filled in the input
 * @param onSave       Called with the new value when user confirms
 * @param onDismiss    Called when dialog is cancelled
 */
@Composable
fun EditFieldDialog(
    fieldLabel: String,
    currentValue: String,
    onSave: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var inputValue by remember { mutableStateOf(currentValue) }
    var isError by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E2233)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {

                // Header row: Edit icon + label
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = PrimaryBlue,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Edit $fieldLabel",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Text input field
                OutlinedTextField(
                    value = inputValue,
                    onValueChange = {
                        inputValue = it
                        isError = false
                    },
                    label = { Text(fieldLabel, fontSize = 12.sp) },
                    isError = isError,
                    supportingText = if (isError) {
                        { Text("$fieldLabel cannot be empty", color = Color(0xFFFF4444), fontSize = 11.sp) }
                    } else null,
                    singleLine = fieldLabel != "ADDRESS",
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = DarkSurface,
                        focusedContainerColor = DarkSurface,
                        unfocusedBorderColor = Color(0xFF3A3F55),
                        focusedBorderColor = PrimaryBlue,
                        cursorColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        unfocusedLabelColor = Color.Gray,
                        focusedLabelColor = PrimaryBlue,
                        errorBorderColor = Color(0xFFFF4444),
                        errorLabelColor = Color(0xFFFF4444)
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Action buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Cancel
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF3A3F55))
                    ) {
                        Text("Cancel", color = Color.Gray)
                    }

                    // Save
                    Button(
                        onClick = {
                            if (inputValue.isBlank()) {
                                isError = true
                            } else {
                                onSave(inputValue.trim())
                            }
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                    ) {
                        Text("Save", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
