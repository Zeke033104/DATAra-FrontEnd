package com.capstone.datara.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.datara.R
import com.capstone.datara.ui.components.CustomTextField
import com.capstone.datara.ui.components.PrimaryButton
import com.capstone.datara.ui.theme.DarkBackground
import com.capstone.datara.ui.theme.PrimaryBlue
import com.capstone.datara.ui.theme.PrimaryGreen

@Composable
fun RegisterScreen(
    isTermsAccepted: Boolean,
    onTermsStateChange: (Boolean) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    onTermsClick: () -> Unit
) {
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Validation error states (visual only)
    var phoneError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }
    var confirmPasswordMessage by remember { mutableStateOf("") }

    fun validate(): Boolean {
        phoneError = phone.isBlank()
        passwordError = password.length < 6
        confirmPasswordError = confirmPassword != password || confirmPassword.isBlank()
        confirmPasswordMessage = when {
            confirmPassword.isBlank() -> "Please confirm your password"
            confirmPassword != password -> "Passwords do not match"
            else -> ""
        }
        return !phoneError && !passwordError && !confirmPasswordError
    }

    // Password match indicator (green tick when both match and not empty)
    val passwordsMatch = password.isNotBlank() && confirmPassword.isNotBlank() && password == confirmPassword

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            // Push content up when keyboard appears
            .imePadding()
            .padding(horizontal = 32.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        // App Logo
        Image(
            painter = painterResource(id = R.drawable.datara_logo),
            contentDescription = "DATAra Logo",
            modifier = Modifier.size(140.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "REGISTER",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Phone Number
        Text(text = "Phone Number", color = Color.White, fontSize = 14.sp, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            value = phone,
            onValueChange = {
                phone = it
                if (phoneError) phoneError = false
            },
            label = "",
            placeholder = "099xxxxxx",
            leadingIcon = Icons.Default.Phone,
            isError = phoneError,
            errorMessage = if (phoneError) "Phone number is required" else null
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Create Password
        Text(text = "Create Password", color = Color.White, fontSize = 14.sp, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            value = password,
            onValueChange = {
                password = it
                if (passwordError) passwordError = false
                if (confirmPasswordError) confirmPasswordError = false
            },
            label = "",
            placeholder = "Min. 6 characters",
            leadingIcon = Icons.Default.Lock,
            isPassword = true,
            isError = passwordError,
            errorMessage = if (passwordError) "Password must be at least 6 characters" else null
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password — label row with match indicator
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Confirm Password", color = Color.White, fontSize = 14.sp)
            if (passwordsMatch) {
                Text("✓ Passwords match", color = PrimaryGreen, fontSize = 12.sp, fontWeight = FontWeight.Medium)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                if (confirmPasswordError) confirmPasswordError = false
            },
            label = "",
            placeholder = "********",
            leadingIcon = Icons.Default.Lock,
            isPassword = true,
            isError = confirmPasswordError,
            errorMessage = if (confirmPasswordError) confirmPasswordMessage else null
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Terms and Conditions Checkbox
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = isTermsAccepted,
                onCheckedChange = { isChecked ->
                    if (isChecked) {
                        onTermsClick()
                    } else {
                        onTermsStateChange(false)
                    }
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = PrimaryGreen,
                    uncheckedColor = Color.Gray,
                    checkmarkColor = Color.White
                )
            )
            Text(text = "Agree to ", color = Color.Gray, fontSize = 14.sp)
            Text(
                text = "Terms and Conditions",
                color = PrimaryBlue,
                fontSize = 14.sp,
                modifier = Modifier.clickable { onTermsClick() }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        PrimaryButton(
            text = "Register",
            onClick = {
                if (validate()) onRegisterClick()
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Text(text = "Already have an account? ", color = Color.Gray, fontSize = 14.sp)
            Text(
                text = "Log In",
                color = PrimaryBlue,
                fontSize = 14.sp,
                modifier = Modifier.clickable { onLoginClick() }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}
