package com.capstone.datara.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.datara.ui.components.EmptyState
import com.capstone.datara.ui.components.NotificationItem

@Composable
fun NotificationScreen(onBackClick: () -> Unit = {}) {

    // Mock notification data — swap isUnread to show read/unread styling
    val notifications = listOf(
        Triple("Data Usage Alert", "You've used 70% of your 14 GB plan. Consider slowing down.", "2 mins ago"),
        Triple("Daily Summary", "Yesterday you used 1.8 GB — above your 1.5 GB daily average.", "1 hour ago"),
        Triple("Budget Limit Reached", "Your 350 MB session budget has been reached.", "3 hours ago"),
        Triple("Prediction Update", "ML model predicts plan depletion in 3 days at current pace.", "Yesterday"),
        Triple("Weekly Report", "Your weekly data usage report is ready to view in History.", "2 days ago")
    )

    // Track which notifications are read (index-based for demo)
    var readSet by remember { mutableStateOf(setOf<Int>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F8))
    ) {
        // ── Header ──────────────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onBackClick() }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    "Notifications",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Mark all as read
            Text(
                "Mark all as read",
                color = Color(0xFF6C63FF),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable {
                    readSet = notifications.indices.toSet()
                }
            )
        }

        // Unread count chip
        if (readSet.size < notifications.size) {
            val unreadCount = notifications.size - readSet.size
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F8))
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Surface(
                    color = Color(0xFF6C63FF).copy(alpha = 0.12f),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp)
                ) {
                    Text(
                        "$unreadCount unread",
                        color = Color(0xFF6C63FF),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }
            }
        }

        // ── List / Empty State ───────────────────────────────────────────────
        if (notifications.isEmpty()) {
            EmptyState(
                icon = Icons.Default.Notifications,
                title = "No Notifications Yet",
                subtitle = "We'll notify you when your data usage needs attention.",
                iconTint = Color(0xFFCCCCCC)
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                notifications.forEachIndexed { index, (title, body, time) ->
                    NotificationItem(
                        title = title,
                        body = body,
                        timestamp = time,
                        isUnread = index !in readSet
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
