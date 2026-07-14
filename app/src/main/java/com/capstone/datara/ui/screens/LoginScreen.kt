package com.capstone.datara.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.capstone.datara.ui.components.PrimaryButton
import com.capstone.datara.ui.components.CustomTextField
import com.capstone.datara.ui.theme.DarkBackground
import com.capstone.datara.ui.theme.PrimaryBlue

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgetPasswordClick: () -> Unit
) {
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Validation error states (visual only)
    var phoneError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    fun validate(): Boolean {
        phoneError = phone.isBlank()
        passwordError = password.isBlank()
        return !phoneError && !passwordError
    }

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
        Spacer(modifier = Modifier.height(80.dp))

        // App Logo
        Image(
            painter = painterResource(id = R.drawable.datara_logo),
            contentDescription = "DATAra Logo",
            modifier = Modifier.size(140.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "LOGIN",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Phone Number
        Text(
            text = "Phone Number",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth()
        )
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

        // Password row header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Password", color = Color.White, fontSize = 14.sp)
            Text(
                text = "Forget Password",
                color = PrimaryBlue,
                fontSize = 14.sp,
                modifier = Modifier.clickable { onForgetPasswordClick() }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            value = password,
            onValueChange = {
                password = it
                if (passwordError) passwordError = false
            },
            label = "",
            placeholder = "********",
            leadingIcon = Icons.Default.Lock,
            isPassword = true,
            isError = passwordError,
            errorMessage = if (passwordError) "Password is required" else null
        )

        Spacer(modifier = Modifier.height(40.dp))

        PrimaryButton(
            text = "Login",
            onClick = {
                if (validate()) onLoginClick()
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Text(text = "Don't have an account? ", color = Color.Gray, fontSize = 14.sp)
            Text(
                text = "Sign up",
                color = PrimaryBlue,
                fontSize = 14.sp,
                modifier = Modifier.clickable { onSignUpClick() }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}
