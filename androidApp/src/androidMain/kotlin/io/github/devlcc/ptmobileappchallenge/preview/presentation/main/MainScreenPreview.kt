package io.github.devlcc.ptmobileappchallenge.preview.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import designsystem.theme.AppTheme
import moe.tlaster.precompose.PreComposeApp
import presentation.main.MainScreen

@Preview
@Composable
fun MainScreenPreview() {
    PreComposeApp {
        AppTheme(/*typography = MaterialTheme.typography*/) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.inverseSurface
            ) {
                MainScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}