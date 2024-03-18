package presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import designsystem.theme.AppColor

@Composable
fun BottomNavigationBar(
    modifier: Modifier,
    currentDestination: MainDestination = MainDestination.Home,
    onNavigate: ((destination: MainDestination) -> Unit) = {},
) {
    Surface(
        modifier = modifier
    ) {
        NavigationBar(
            modifier = Modifier.padding(
                horizontal = 36.dp,
                vertical = 8.dp
            ),
            containerColor = Color.White,
            contentColor = AppColor.BlueAccent,
            tonalElevation = 24.dp,
        ) {
            HomeNavigationItem.buildNavigationItems(
                currentDestination = currentDestination,
                onNavigate = onNavigate
            ).forEach { destination ->
                NavigationBarItem(
                    modifier = Modifier.align(Alignment.Top),
                    selected = destination.selected,
                    onClick = destination.onClick,
                    icon = destination.icon,
                    alwaysShowLabel = false,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AppColor.BlueAccent,
                        selectedTextColor = AppColor.BlueAccent,
                        indicatorColor = Color.Transparent,
                        unselectedIconColor = Color.White,
                        unselectedTextColor = Color.White,
                    ),
                )
            }
        }
    }
}