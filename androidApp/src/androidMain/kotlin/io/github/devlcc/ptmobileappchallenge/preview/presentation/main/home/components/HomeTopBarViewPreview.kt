package io.github.devlcc.ptmobileappchallenge.preview.presentation.main.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import designsystem.theme.AppTheme
import presentation.main.home.components.HomeTopBarView

@Preview
@Composable
fun HomeTopBarView_Preview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface
        ) {
            HomeTopBarView(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}