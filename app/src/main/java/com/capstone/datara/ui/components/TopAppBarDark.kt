package com.capstone.datara.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Shared dark top app bar used across Dashboard, History, and future screens.
 * Shows the carrier badge, phone number, notification bell, and user avatar.
 */
@Composable
fun TopAppBarDark(
    carrierLabel: String = "TM",
    phoneNumber: String = "+6308312035",
    onNotificationClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                Modifier.windowInsetsPadding(
                    WindowInsets.statusBars
                )
            )
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Left: carrier badge + phone number
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                color = Color.DarkGray,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(end = 12.dp)
            ) {
                Text(
                    text = carrierLabel,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
            Text(phoneNumber, color = Color.White, fontSize = 16.sp)
        }

        // Right: bell + avatar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onNotificationClick() }
            )
            Surface(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .clickable { onProfileClick() },
                color = Color(0xFF3A3F55)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("U", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
