package designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * App filled button with generic content slot. Wraps Material 3 [Button].
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param small Whether or not the size of the button should be small or regular.
 * @param colors [ButtonColors] that will be used to resolve the container and content color for
 * this button in different states. See [AppButtonDefaults.filledButtonColors].
 * @param contentPadding The spacing values to apply internally between the container and the
 * content. See [AppButtonDefaults.buttonContentPadding].
 * @param content The button content.
 */

@Composable
fun AppFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    colors: ButtonColors = AppButtonDefaults.filledButtonColors(),
    cornerRadius: Dp = 4.dp,
    contentPadding: PaddingValues = AppButtonDefaults.buttonContentPadding(small = small),
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = if (small) {
            modifier.heightIn(min = AppButtonDefaults.SmallButtonHeight)
        } else {
            modifier
               },
        enabled = enabled,
        colors = colors,
        contentPadding = contentPadding,
        shape = RoundedCornerShape(cornerRadius),
        content = {
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                content()
            }
        }
    )
}

/**
 * App filled button with text and icon content slots.
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param small Whether or not the size of the button should be small or regular.
 * @param colors [ButtonColors] that will be used to resolve the container and content color for
 * this button in different states. See [AppButtonDefaults.filledButtonColors].
 * @param text The button text label content.
 * @param leadingIcon The button leading icon content. Pass `null` here for no leading icon.
 * @param trailingIcon The button trailing icon content. Pass `null` here for no trailing icon.
 * @param contentPadding The spacing values to apply internally between the container and the
 * content. See [AppButtonDefaults.buttonContentPadding].
 */
@Composable
fun AppFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    colors: ButtonColors = AppButtonDefaults.filledButtonColors(),
    cornerRadius: Dp = 24.dp,
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    contentPadding: PaddingValues = AppButtonDefaults.buttonContentPadding(
        small = small,
        leadingIcon = leadingIcon != null,
        trailingIcon = trailingIcon != null
    ),
) {
    AppFilledButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        small = small,
        colors = colors,
        cornerRadius = cornerRadius,
        contentPadding = contentPadding,
    ) {
        AppButtonContent(
            text = {
                ProvideTextStyle(
                    value = MaterialTheme.typography.labelSmall.copy(
                        color = when(enabled) {
                            true -> MaterialTheme.typography.labelSmall.color
                            false -> MaterialTheme.colorScheme.outline
                        }
                    )
                ) {
                    text()
                }
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
    }
}

@Composable
fun AppTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    colors: ButtonColors = AppButtonDefaults.textButtonColors(),
    cornerRadius: Dp = 4.dp,
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    contentPadding: PaddingValues = AppButtonDefaults.buttonContentPadding(
        small = small,
        leadingIcon = leadingIcon != null,
        trailingIcon = trailingIcon != null
    ),
) {
    AppFilledButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        small = small,
        colors = colors,
        cornerRadius = cornerRadius,
        contentPadding = contentPadding,
    ) {
        AppButtonContent(
            text = {
                ProvideTextStyle(
                    value = MaterialTheme.typography.labelSmall.copy(
                        color = when(enabled) {
                            true -> MaterialTheme.typography.labelSmall.color
                            false -> MaterialTheme.colorScheme.outline
                        }
                    )
                ) {
                    text()
                }
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
    }
}

/**
 * Internal App button content layout for arranging the text label, leading icon and
 * trailing icon.
 *
 * @param text The button text label content.
 * @param leadingIcon The button leading icon content. Pass `null` here for no leading icon.
 * @param trailingIcon The button trailing icon content. Pass `null` here for no trailing icon.
 */
@Composable
private fun RowScope.AppButtonContent(
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?
) {
    if (leadingIcon != null) {
        Box(Modifier.sizeIn(maxHeight = AppButtonDefaults.ButtonIconSize)) {
            leadingIcon()
        }
    }
    Box(
        Modifier
            .padding(
                start = if (leadingIcon != null) {
                    AppButtonDefaults.ButtonContentSpacing
                } else {
                    0.dp
                },
                end = if (trailingIcon != null) {
                    AppButtonDefaults.ButtonContentSpacing
                } else {
                    0.dp
                }
            )
    ) {
        text()
    }
    if (trailingIcon != null) {
        Box(Modifier.sizeIn(maxHeight = AppButtonDefaults.ButtonIconSize)) {
            trailingIcon()
        }
    }
}

/**
 * App button default values.
 */
object AppButtonDefaults {
    val SmallButtonHeight = 32.dp
    const val DisabledButtonContainerAlpha = 0.85f/*0.12f*/
    const val DisabledButtonContentAlpha = 0.85f/*0.38f*/
    val ButtonHorizontalPadding = 12.dp/*24.dp*/
    val ButtonHorizontalIconPadding = 12.dp/*16.dp*/
    val ButtonVerticalPadding = 4.dp/*8.dp*/
    val SmallButtonHorizontalPadding = 12.dp/*16.dp*/
    val SmallButtonHorizontalIconPadding = 8.dp/*12.dp*/
    val SmallButtonVerticalPadding = 4.dp/*7.dp*/
    val ButtonContentSpacing = 4.dp/*8.dp*/
    val ButtonIconSize = 8.dp/*18.dp*/
    fun buttonContentPadding(
        small: Boolean,
        leadingIcon: Boolean = false,
        trailingIcon: Boolean = false
    ): PaddingValues {
        return PaddingValues(
            start = when {
                small && leadingIcon -> SmallButtonHorizontalIconPadding
                small -> SmallButtonHorizontalPadding
                leadingIcon -> ButtonHorizontalIconPadding
                else -> ButtonHorizontalPadding
            },
            top = if (small) SmallButtonVerticalPadding else ButtonVerticalPadding,
            end = when {
                small && trailingIcon -> SmallButtonHorizontalIconPadding
                small -> SmallButtonHorizontalPadding
                trailingIcon -> ButtonHorizontalIconPadding
                else -> ButtonHorizontalPadding
            },
            bottom = if (small) SmallButtonVerticalPadding else ButtonVerticalPadding
        )
    }
    @Composable
    fun filledButtonColors(
        containerColor: Color = MaterialTheme.colorScheme.onBackground,
        contentColor: Color = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor: Color = containerColor.copy(
            alpha = DisabledButtonContainerAlpha
        ),
        disabledContentColor: Color = contentColor.copy(
            alpha = DisabledButtonContentAlpha
        )
    ) = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )
    @Composable
    fun outlinedButtonBorder(
        enabled: Boolean,
        width: Dp = 1.dp,
        color: Color = MaterialTheme.colorScheme.onBackground,
        disabledColor: Color = MaterialTheme.colorScheme.onBackground.copy(
            alpha = DisabledButtonContainerAlpha
        )
    ): BorderStroke = BorderStroke(
        width = width,
        color = if (enabled) color else disabledColor
    )
    @Composable
    fun outlinedButtonColors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = MaterialTheme.colorScheme.onBackground,
        disabledContainerColor: Color = Color.Transparent,
        disabledContentColor: Color = contentColor.copy(
            alpha = DisabledButtonContentAlpha
        )
    ) = ButtonDefaults.outlinedButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )
    @Composable
    fun textButtonColors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = MaterialTheme.colorScheme.onBackground,
        disabledContainerColor: Color = Color.Transparent,
        disabledContentColor: Color = contentColor.copy(
            alpha = DisabledButtonContentAlpha
        )
    ) = ButtonDefaults.textButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun iconButtonColors(
        containerColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
        contentColor: Color = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor: Color = containerColor.copy(
            alpha = DisabledButtonContainerAlpha
        ),
        disabledContentColor: Color = contentColor.copy(alpha = DisabledButtonContentAlpha)
    ) = IconButtonDefaults.iconButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
    )
}