package com.example.blockchainapp.view

import android.widget.ImageView
import androidx.compose.animation.core.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.blockchainapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val rotationState = rememberInfiniteTransition(label = "")
    val rotation by rotationState.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(2000)),
        label = "",
    )

    val tmdbIcon = painterResource(id = R.drawable.saul)
    LaunchedEffect(true) {
        delay(2000)
        navController.navigate("MainPage")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_theme))
            .graphicsLayer(rotationZ = rotation),
    ) {
        Image(
            painter = tmdbIcon,
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .align(Center),
        )
    }
}
