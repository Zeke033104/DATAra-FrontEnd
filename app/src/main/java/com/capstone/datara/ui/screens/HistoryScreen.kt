package com.capstone.datara.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.datara.ui.components.TopAppBarDark
import com.capstone.datara.ui.components.FilterBottomSheet
import com.capstone.datara.ui.theme.PrimaryBlue

@Composable
fun HistoryScreen(
    onNotificationClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf("Daily") }
    val tabs = listOf("Daily", "Weekly", "Monthly")
    var showFilterSheet by remember { mutableStateOf(false) }
    var activeFilter by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top App Bar — shared component
        TopAppBarDark(
            onNotificationClick = onNotificationClick,
            onProfileClick = onProfileClick
        )

        // ── Filter Bottom Sheet ──────────────────────────────────────────
        if (showFilterSheet) {
            FilterBottomSheet(
                onApply = { dateRange, dataRange ->
                    activeFilter = "$dateRange · $dataRange"
                    showFilterSheet = false
                },
                onDismiss = { showFilterSheet = false }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Header row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("HISTORY", fontWeight = FontWeight.ExtraBold, fontSize = 26.sp, color = MaterialTheme.colorScheme.onBackground)
                IconButton(onClick = { showFilterSheet = true }) {
                    Icon(Icons.Default.Settings, contentDescription = "Filter", tint = MaterialTheme.colorScheme.onBackground)
                }
            }

            // Active filter indicator
            if (activeFilter.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Filtered: $activeFilter",
                    color = com.capstone.datara.ui.theme.PrimaryBlue,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Chart Card with Line Graph
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column {
                            Text("TOTAL THIS DAY", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                            Row(verticalAlignment = Alignment.Bottom) {
                                Text("25.00", fontWeight = FontWeight.Bold, fontSize = 28.sp, color = MaterialTheme.colorScheme.onSurface)
                                Text(" GB", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(bottom = 4.dp))
                            }
                        }
                        // Tab selector
                        Row(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20.dp))
                                .padding(4.dp)
                        ) {
                            tabs.forEach { tab ->
                                val isSelected = selectedTab == tab
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(16.dp))
                                        .clickable { selectedTab = tab }
                                        .background(if (isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background)
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        tab, fontSize = 12.sp,
                                        color = if (isSelected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Y-axis labels + Line Chart
                    val dataPoints = listOf(2f, 4f, 6f, 5f, 4f, 5.5f, 7f)
                    val xLabels = listOf("1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00")
                    val maxVal = 10f

                    Row(modifier = Modifier.fillMaxWidth().height(140.dp)) {
                        // Y-axis labels
                        Column(
                            modifier = Modifier.fillMaxHeight().padding(end = 4.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                        listOf("10", "8", "6", "4", "2").forEach {
                                Text(it, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }

                        // Canvas line graph
                        Canvas(modifier = Modifier.weight(1f).fillMaxHeight()) {
                            val w = size.width
                            val h = size.height
                            val stepX = w / (dataPoints.size - 1)

                            val points = dataPoints.mapIndexed { i, v ->
                                Offset(i * stepX, h - (v / maxVal * h))
                            }

                            // Draw grid lines
                            for (i in 0..4) {
                                val y = h * i / 4
                                drawLine(
                                    color = Color(0xFFEEEEEE),
                                    start = Offset(0f, y),
                                    end = Offset(w, y),
                                    strokeWidth = 1f
                                )
                            }

                            // Draw smooth line using Path
                            val path = Path()
                            path.moveTo(points[0].x, points[0].y)
                            for (i in 1 until points.size) {
                                val prev = points[i - 1]
                                val curr = points[i]
                                val cx = (prev.x + curr.x) / 2
                                path.cubicTo(cx, prev.y, cx, curr.y, curr.x, curr.y)
                            }

                            // Fill area under the curve
                            val fillPath = Path()
                            fillPath.addPath(path)
                            fillPath.lineTo(points.last().x, h)
                            fillPath.lineTo(points.first().x, h)
                            fillPath.close()

                            drawPath(
                                fillPath,
                                color = PrimaryBlue.copy(alpha = 0.12f)
                            )

                            // Draw the line itself
                            drawPath(
                                path,
                                color = PrimaryBlue,
                                style = Stroke(width = 3f)
                            )

                            // Draw data point dots
                            points.forEach { p ->
                                drawCircle(color = PrimaryBlue, radius = 5f, center = p)
                                drawCircle(color = Color.White, radius = 3f, center = p)
                            }
                        }
                    }

                    // X-axis labels
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(start = 28.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                    xLabels.forEach {
                            Text(it, fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Detail Logs header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("DETAIL LOGS", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Filter", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Medium)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Log entries
            val logs = listOf(
                Triple("May 07, 2026", "15gb", "12:59pm"),
                Triple("May 07, 2026", "7gb", "12:59pm"),
                Triple("May 09, 2026", "6gb", "12:59pm")
            )
            logs.forEach { (date, data, time) ->
                LogEntry(date = date, time = time, dataConsumed = data)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun LogEntry(date: String, time: String, dataConsumed: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(date, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("Data consumed", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("Session Duration", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("Peak Speed", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(time, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                Text(dataConsumed, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                Text("2h 15m", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                Text("4.2mb/s", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}
