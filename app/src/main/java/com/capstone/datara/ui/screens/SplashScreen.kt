package com.capstone.datara.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.capstone.datara.R
import com.capstone.datara.ui.theme.DarkBackground
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateToLogin: () -> Unit) {

    // Animate alpha: 0 → 1 over 800ms
    val alpha = remember { Animatable(0f) }
    // Animate scale: 0.7 → 1.0 over 900ms with overshoot spring
    val scale = remember { Animatable(0.7f) }

    LaunchedEffect(Unit) {
        // Run both animations simultaneously
        launch {
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
            )
        }
        launch {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            )
        }
        // Hold for total of 2.5 seconds then navigate
        delay(2500L)
        onNavigateToLogin()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.datara_logo),
            contentDescription = "DATAra Logo",
            modifier = Modifier
                .size(180.dp)
                .scale(scale.value)
                .alpha(alpha.value)
        )
    }
}
