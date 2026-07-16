package com.capstone.datara.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.datara.ui.theme.DarkBackground
import com.capstone.datara.ui.theme.DarkSurface
import com.capstone.datara.ui.theme.PrimaryGreen

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit = {},
    onDeleteAccountClick: () -> Unit = {},
    onCsvManagementClick: () -> Unit,
    isDarkMode: Boolean = true,
    onDarkModeToggle: (Boolean) -> Unit = {}
) {
    var notificationsEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Settings",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(32.dp))

        // ── Theme Toggle ─────────────────────────────────────────────────────
        SettingsToggleRow(
            label = "Dark Mode",
            description = if (isDarkMode) "Currently: Dark" else "Currently: Light",
            checked = isDarkMode,
            onCheckedChange = onDarkModeToggle
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ── Notifications Toggle ──────────────────────────────────────────────
        SettingsToggleRow(
            label = "Push Notifications",
            description = if (notificationsEnabled) "Alerts are on" else "Alerts are off",
            checked = notificationsEnabled,
            onCheckedChange = { notificationsEnabled = it }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ── CSV Management ────────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onCsvManagementClick() }
                .background(DarkSurface, RoundedCornerShape(12.dp))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Manage CSV Data", color = Color.White, fontSize = 16.sp)
                Text("Import / export usage logs", color = Color.Gray, fontSize = 12.sp)
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

/**
 * Reusable toggle row for settings items.
 */
@Composable
fun SettingsToggleRow(
    label: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkSurface, RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(label, color = Color.White, fontSize = 16.sp)
            Text(description, color = Color.Gray, fontSize = 12.sp)
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = PrimaryGreen,
                uncheckedThumbColor = Color.Gray,
                uncheckedTrackColor = Color(0xFF3A3F55)
            )
        )
    }
}
