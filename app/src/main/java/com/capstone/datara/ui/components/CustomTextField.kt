package com.capstone.datara.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.datara.ui.theme.ErrorRed

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val visualTransformation = when {
        isPassword && !passwordVisible -> PasswordVisualTransformation()
        else -> VisualTransformation.None
    }

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            placeholder = {
                Text(
                    text = placeholder,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = if (isError) ErrorRed else MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            trailingIcon = if (isPassword) {
                {
                    Text(
                        text = if (passwordVisible) "Hide" else "Show",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .clickable { passwordVisible = !passwordVisible }
                            .padding(end = 8.dp)
                    )
                }
            } else null,
            visualTransformation = visualTransformation,
            keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password)
                             else KeyboardOptions.Default,
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor  = MaterialTheme.colorScheme.surface,
                focusedContainerColor    = MaterialTheme.colorScheme.surface,
                unfocusedBorderColor     = if (isError) ErrorRed else MaterialTheme.colorScheme.outline,
                focusedBorderColor       = if (isError) ErrorRed else MaterialTheme.colorScheme.primary,
                errorBorderColor         = ErrorRed,
                unfocusedLabelColor      = if (isError) ErrorRed else MaterialTheme.colorScheme.onSurfaceVariant,
                focusedLabelColor        = if (isError) ErrorRed else MaterialTheme.colorScheme.primary,
                unfocusedTextColor       = MaterialTheme.colorScheme.onSurface,
                focusedTextColor         = MaterialTheme.colorScheme.onSurface,
                cursorColor              = MaterialTheme.colorScheme.primary
            ),
            singleLine = true
        )

        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = ErrorRed,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}
