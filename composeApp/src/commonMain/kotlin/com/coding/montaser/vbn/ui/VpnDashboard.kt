package com.coding.montaser.vbn.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VpnDashboard() {
    val isConnected = remember { mutableStateOf(false) }
    
    // Main Container
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF0F172A), Color(0xFF1E293B))))
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Text(
                text = "FRIEND ZONE",
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 4.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            // The Connection Core (The "Good" Part)
            ConnectionButton(isConnected.value) {
                isConnected.value = !isConnected.value
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Status Text
            Text(
                text = if (isConnected.value) "PROTECTED" else "UNPROTECTED",
                color = if (isConnected.value) Color(0xFF4ADE80) else Color(0xFFF87171),
                style = MaterialTheme.typography.button,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.weight(1f))

            // Server Selection Card (Glass-morphism)
            ServerCard(location = "New York, USA", flag = "🇺🇸")
        }
    }
}

@Composable
fun ConnectionButton(connected: Boolean, onClick: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition()
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(contentAlignment = Alignment.Center) {
        // Outer Glow
        Canvas(modifier = Modifier.size(240.dp)) {
            drawCircle(
                color = if (connected) Color(0xFF22C55E) else Color(0xFF3B82F6),
                radius = size.minDimension / 2,
                alpha = if (connected) glowAlpha else 0.1f
            )
        }
        
        // Main Button
        Button(
            onClick = onClick,
            shape = CircleShape,
            modifier = Modifier.size(160.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF1E293B),
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.elevation(defaultElevation = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.PowerSettingsNew,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = if (connected) Color(0xFF4ADE80) else Color.White
            )
        }
    }
}