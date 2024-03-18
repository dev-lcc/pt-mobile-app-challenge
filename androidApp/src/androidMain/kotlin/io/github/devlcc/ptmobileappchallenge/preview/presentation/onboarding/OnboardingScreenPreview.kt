package io.github.devlcc.ptmobileappchallenge.preview.presentation.onboarding

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import designsystem.theme.AppTheme
import presentation.onboarding.OnboardingScreen

@Preview
@Composable
fun Preview_OnboardingScreen() {
    AppTheme(/*typography = MaterialTheme.typography*/) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            OnboardingScreen(modifier = Modifier.fillMaxSize())
        }
    }
}