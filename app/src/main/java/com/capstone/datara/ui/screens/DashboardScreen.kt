package com.capstone.datara.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.datara.ui.components.TopAppBarDark
import com.capstone.datara.ui.components.NotificationPermissionDialog
import com.capstone.datara.ui.theme.PrimaryBlue
import com.capstone.datara.ui.theme.PrimaryGreen
import kotlinx.coroutines.delay

@Composable
fun DashboardScreen(
    onNotificationClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    // ── Animation triggers ────────────────────────────────────────────
    // Notification permission dialog — shown once on first load after a short delay
    var showNotifDialog by remember { mutableStateOf(false) }

    // Card stagger visibility
    var card1Visible by remember { mutableStateOf(false) }
    var card2Visible by remember { mutableStateOf(false) }
    var card3Visible by remember { mutableStateOf(false) }
    var card4Visible by remember { mutableStateOf(false) }

    // Animated progress fill (0 → 70%)
    val progressAnim by animateFloatAsState(
        targetValue = if (card1Visible) 0.7f else 0f,
        animationSpec = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
        label = "progressBar"
    )

    // Animated counter (0 → 70)
    val counterAnim by animateIntAsState(
        targetValue = if (card1Visible) 70 else 0,
        animationSpec = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
        label = "counter"
    )

    // Stagger card appearances with delays
    LaunchedEffect(Unit) {
        card1Visible = true
        delay(150)
        card2Visible = true
        delay(150)
        card3Visible = true
        delay(150)
        card4Visible = true
        // Show notification permission dialog 800ms after cards appear
        delay(800)
        showNotifDialog = true
    }

    // ── Notification Permission Dialog ────────────────────────────────────
    if (showNotifDialog) {
        NotificationPermissionDialog(
            onAllowClick = { showNotifDialog = false },
            onDenyClick = { showNotifDialog = false },
            onDismiss = { showNotifDialog = false }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1C23))
    ) {
        // ── Reusable top bar ─────────────────────────────────────────────
        TopAppBarDark(
            onNotificationClick = onNotificationClick,
            onProfileClick = onProfileClick
        )

        // ── Scrollable Content ───────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEEEFF4))
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // ── Card 1: Main Usage ───────────────────────────────────────
            AnimatedVisibility(
                visible = card1Visible,
                enter = slideInVertically(
                    animationSpec = tween(400),
                    initialOffsetY = { it / 2 }
                ) + fadeIn(tween(400))
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            StatItem("Total Used", "7 GB", "OUT OF 14 GB")
                            StatItem("Predicted", "8hrs", "LEFT")
                            StatItem("Daily Avg", "1.5 GB", "PER DAY")
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Animated progress bar
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(22.dp)
                                .clip(RoundedCornerShape(11.dp))
                                .background(Color(0xFFEEEEEE))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(progressAnim)
                                    .fillMaxHeight()
                                    .clip(RoundedCornerShape(11.dp))
                                    .background(PrimaryGreen)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Animated counter
                        Text(
                            "$counterAnim%",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text("Consumption Rate", fontSize = 14.sp, color = Color.Gray)

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                            shape = RoundedCornerShape(24.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("USAGE: NORMAL PACE", fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ── Card 2: Data Budget ──────────────────────────────────────
            AnimatedVisibility(
                visible = card2Visible,
                enter = slideInVertically(
                    animationSpec = tween(400),
                    initialOffsetY = { it / 2 }
                ) + fadeIn(tween(400))
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("SET DATA BUDGET", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = { },
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier.weight(1f).height(80.dp).padding(end = 8.dp)
                            ) {
                                Text("▶  START", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            }
                            Button(
                                onClick = { },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A1C23)),
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier.weight(1f).height(80.dp).padding(start = 8.dp)
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("SET LIMIT", fontSize = 13.sp, color = Color.White)
                                    Text("350mb", fontWeight = FontWeight.Bold, color = Color.White)
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ── Card 3: Data Consumption ─────────────────────────────────
            AnimatedVisibility(
                visible = card3Visible,
                enter = slideInVertically(
                    animationSpec = tween(400),
                    initialOffsetY = { it / 2 }
                ) + fadeIn(tween(400))
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Surface(
                                    shape = CircleShape,
                                    color = PrimaryBlue.copy(alpha = 0.15f),
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text("📶", fontSize = 14.sp)
                                    }
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Data Consumption", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 14.sp)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(verticalAlignment = Alignment.Bottom) {
                                Text("250", fontSize = 48.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)
                                Text(" MB /min", fontSize = 12.sp, color = PrimaryBlue, modifier = Modifier.padding(bottom = 10.dp))
                            }
                            Text("SEE DETAILS →", color = PrimaryBlue, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }

                        // Mini bar chart
                        Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.height(80.dp)) {
                            listOf(40, 60, 50, 80).forEachIndexed { i, h ->
                                if (i > 0) Spacer(modifier = Modifier.width(4.dp))
                                Box(
                                    modifier = Modifier
                                        .width(14.dp)
                                        .height(h.dp)
                                        .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                                        .background(PrimaryBlue)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ── Card 4: Data Insight (dark) ──────────────────────────────
            AnimatedVisibility(
                visible = card4Visible,
                enter = slideInVertically(
                    animationSpec = tween(400),
                    initialOffsetY = { it / 2 }
                ) + fadeIn(tween(400))
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1C23)),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text("DATA INSIGHT", fontWeight = FontWeight.Bold, color = PrimaryGreen, fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(12.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            InsightItem("Avg Speed", "4.2 mb/s", PrimaryBlue)
                            InsightItem("Peak Hour", "2:00 PM", Color(0xFFFFA500))
                            InsightItem("Est. Depletion", "3 Days", Color(0xFFFF4444))
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Surface(
                            color = Color(0xFF2A2F45),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "⚡ ML Prediction: At your current pace, your 14 GB plan will deplete in approximately 3 days.",
                                color = Color.LightGray,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(12.dp),
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun StatItem(title: String, value: String, subtitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, fontSize = 12.sp, color = Color.Gray)
        Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = subtitle, fontSize = 10.sp, color = Color.Gray)
    }
}

@Composable
fun InsightItem(label: String, value: String, valueColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, fontSize = 11.sp, color = Color.Gray)
        Text(value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = valueColor)
    }
}
