package io.github.devlcc.ptmobileappchallenge.preview.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import designsystem.component.AppFilledButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import designsystem.component.AppButtonDefaults
import designsystem.component.AppTextButton
import designsystem.theme.AppColor

@Preview(group = "AppFilledButton", widthDp = 240)
@Composable
fun Preview_AppFilledButton_Enabled() {
    MaterialTheme {
        AppFilledButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = AppButtonDefaults.filledButtonColors(
                containerColor = AppColor.BlueAccent,
                contentColor = Color.White
            ),
            cornerRadius = 24.dp,
            enabled = true,
        ) {
            Text("Explore")
        }
    }
}

@Preview(group = "AppFilledButton", widthDp = 240)
@Composable
fun Preview_AppFilledButton_Disabled() {
    AppFilledButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        enabled = false,
        colors = AppButtonDefaults.filledButtonColors(
            containerColor = AppColor.GreenAccent,
            contentColor = Color.White
        ),
        text = {
            Text(text = "Book now")
        },
    )
}

@Preview(group = "AppFilledButton", widthDp = 120)
@Composable
fun AppFilledButtonPreview_Action() {
    AppFilledButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        small = true,
        colors = AppButtonDefaults.filledButtonColors(
            containerColor = AppColor.GreenAccent,
            contentColor = AppColor.GreenAccent,
            disabledContainerColor = AppColor.GreenAccent.copy(alpha = 0.33f),
            disabledContentColor = AppColor.GreenAccent.copy(alpha = 0.33f),
        ),
        contentPadding = PaddingValues(horizontal = 0.dp),
        text = {
            Text(
                text = "Book Now",
                style = MaterialTheme.typography.labelSmall,
                fontSize = TextUnit(9f, TextUnitType.Sp),
                color = Color.White,
            )
        },
    )
}

@Preview(group = "AppFilledButton", widthDp = 60)
@Composable
fun AppFilledButtonPreview_Positive() {
    AppFilledButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        small = true,
        colors = AppButtonDefaults.filledButtonColors(
            containerColor = AppColor.BlueAccent,
            contentColor = AppColor.BlueAccent,
            disabledContainerColor = AppColor.BlueAccent.copy(alpha = 0.33f),
            disabledContentColor = AppColor.BlueAccent.copy(alpha = 0.33f),
        ),
        contentPadding = PaddingValues(horizontal = 0.dp),
        text = {
            Text(
                text = "Explore",
                style = MaterialTheme.typography.labelSmall,
                fontSize = TextUnit(9f, TextUnitType.Sp),
                color = Color.White,
            )
        },
    )
}

@Preview(group = "AppFilledButton", widthDp = 80)
@Composable
fun AppFilledButtonPreview_Negative() {
    AppFilledButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        small = true,
        colors = AppButtonDefaults.filledButtonColors(
            containerColor = AppColor.DarkerBlueAccent,
            contentColor = AppColor.DarkerBlueAccent,
            disabledContainerColor = AppColor.DarkerBlueAccent.copy(alpha = (0x59).toFloat()/255f),
            disabledContentColor = AppColor.DarkerBlueAccent.copy(alpha = (0x59).toFloat()/255f),
        ),
        contentPadding = PaddingValues(horizontal = 0.dp),
        text = {
            Text(
                text = "Add Rating",
                style = MaterialTheme.typography.labelSmall,
                fontSize = TextUnit(9f, TextUnitType.Sp),
                color = Color.White,
            )
        },
    )
}

@Preview(group = "AppTextButton")
@Composable
fun AppTextButtonPreview() {
    AppTextButton(
        onClick = {},
        modifier = Modifier,
        small = true,
        colors = AppButtonDefaults.textButtonColors(
            containerColor = Color.Transparent,
            contentColor = AppColor.GreenAccent
        ),
        text = {
            Text(text = "Join Now")
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
                contentDescription = "Call Agent",
                modifier = Modifier
                    .height(32.dp)
                    .width(16.dp)
                    .scale(1.25f, 1.25f)
                    .offset(x = 2.dp, y = (0.5).dp)
            )
        },
        contentPadding = PaddingValues(
            start = 0.dp,
            end = 8.dp,
            top = 0.dp,
            bottom = 0.dp,
        ),
    )
}