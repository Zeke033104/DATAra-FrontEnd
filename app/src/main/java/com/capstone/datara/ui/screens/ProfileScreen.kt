package com.capstone.datara.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.datara.ui.components.EditFieldDialog

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit = {},
    onDeleteAccountClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    // Editable field states (mock data — no backend)
    var name by remember { mutableStateOf("Charlie C. Omongos") }
    var email by remember { mutableStateOf("Omongos.charlie@example.com") }
    var address by remember { mutableStateOf("California Cogon City") }

    // Which field dialog is currently open (null = none)
    var editingField by remember { mutableStateOf<String?>(null) }

    // Show edit dialog when a field is tapped
    if (editingField != null) {
        val currentValue = when (editingField) {
            "NAME" -> name
            "EMAIL" -> email
            "ADDRESS" -> address
            else -> ""
        }
        EditFieldDialog(
            fieldLabel = editingField!!,
            currentValue = currentValue,
            onSave = { newValue ->
                when (editingField) {
                    "NAME" -> name = newValue
                    "EMAIL" -> email = newValue
                    "ADDRESS" -> address = newValue
                }
                editingField = null
            },
            onDismiss = { editingField = null }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // ── Dark Top Section ─────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF13171F))
                .padding(top = 20.dp, bottom = 32.dp, start = 16.dp, end = 16.dp)
        ) {
            // Back arrow top-left
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(24.dp)
                    .clickable { onBackClick() }
            )

            // Edit icon top-right
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit Profile",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(22.dp)
                    .clickable { editingField = "NAME" }
            )

            // Avatar + label centered
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile picture circle
                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = CircleShape,
                    color = Color(0xFF3A5FA0)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("😎", fontSize = 56.sp)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = name,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "PROFILE PHOTO",
                    color = Color.Gray,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 1.sp
                )
            }
        }

        // ── White Content Section ─────────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(28.dp))

            // Phone Number (read-only — no chevron)
            ProfileField(
                label = "PHONE NUMBER",
                value = "+6308312035",
                clickable = false,
                onClick = {}
            )

            Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)

            // Name (editable)
            ProfileField(
                label = "NAME",
                value = name,
                clickable = true,
                onClick = { editingField = "NAME" }
            )

            Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)

            // Email (editable)
            ProfileField(
                label = "EMAIL",
                value = email,
                clickable = true,
                onClick = { editingField = "EMAIL" }
            )

            Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)

            // Address (editable)
            ProfileField(
                label = "ADDRESS",
                value = address,
                clickable = true,
                onClick = { editingField = "ADDRESS" }
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Delete Account Button
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    onClick = onDeleteAccountClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(horizontal = 32.dp, vertical = 10.dp)
                ) {
                    Text(
                        "DELETE ACCOUNT",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun ProfileField(
    label: String,
    value: String,
    clickable: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(if (clickable) Modifier.clickable { onClick() } else Modifier)
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontSize = 13.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = value,
                fontSize = 15.sp,
                color = Color(0xFF555555)
            )
        }
        if (clickable) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}
