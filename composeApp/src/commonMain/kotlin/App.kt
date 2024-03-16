import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import ptmobileappchallenge.composeapp.generated.resources.Res
import ptmobileappchallenge.composeapp.generated.resources.compose_multiplatform

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}

@Composable
@Preview
fun AppButton() {
    MaterialTheme {
        Column(
            Modifier.padding(16.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                ) {
                Text("Now is the time!!!")
            }
        }
        
    }
}

@Composable
@Preview
fun AppTitle() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.DarkGray
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                text = "Aspen",
                style = TextStyle(
                    fontSize = TextUnit(96f, TextUnitType.Sp),
                    color = Color.White,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                ),
            )
        }
        
    }
}