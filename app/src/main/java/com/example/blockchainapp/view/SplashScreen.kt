package com.example.blockchainapp.view

import androidx.compose.animation.core.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.blockchainapp.R

@Composable
fun SplashScreen() {
    val rotationState = rememberInfiniteTransition(label = "")
    val rotation by rotationState.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(2000)),
        label = "",
    )

    val tmdbIcon = painterResource(id = R.drawable.tmdb_icon)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_theme))
            .graphicsLayer(rotationZ = rotation),
    ) {
        Icon(
            painter = tmdbIcon,
            contentDescription = null,
            modifier = Modifier
                .background(colorResource(id = R.color.transparent))
                .fillMaxSize(),
        )
    }
}
