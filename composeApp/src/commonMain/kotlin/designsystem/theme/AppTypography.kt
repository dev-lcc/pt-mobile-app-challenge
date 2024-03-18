package designsystem.theme


import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import ptmobileappchallenge.composeapp.generated.resources.Montserrat_Medium
import ptmobileappchallenge.composeapp.generated.resources.Montserrat_Regular
import ptmobileappchallenge.composeapp.generated.resources.Montserrat_SemiBold
import ptmobileappchallenge.composeapp.generated.resources.Res

// Set of Material typography styles to start with
@OptIn(ExperimentalResourceApi::class)
@Composable
fun AppTypography(): Typography {
    return Typography(
        displayLarge = TextStyle(
            fontFamily = FontFamily(Font(resource = Res.font.Montserrat_Regular, weight = FontWeight.Normal)),
            fontWeight = FontWeight.Normal,
            fontSize = 96.sp,   // 116px    i.e. Aspen
            lineHeight = 112.sp,
            letterSpacing = (-1.5).sp
        ),
        headlineMedium = TextStyle(
            fontFamily = FontFamily(Font(resource = Res.font.Montserrat_Medium, weight = FontWeight.Medium)),
            fontWeight = FontWeight.Medium,
            fontSize = 34.sp,   // 40px i.e. "Plan your"
            lineHeight = 36.sp,
            letterSpacing = 0.25.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = FontFamily(Font(resource = Res.font.Montserrat_Medium, weight = FontWeight.Medium)),
            fontWeight = FontWeight.Medium,
            fontSize = 60.sp,   // 40px i.e. Luxurious Vacation
            lineHeight = 72.sp,
            letterSpacing = (-0.5).sp
        ),
        titleLarge = TextStyle(
            fontFamily = FontFamily(Font(resource = Res.font.Montserrat_Medium, weight = FontWeight.Medium)),
            fontWeight = FontWeight.Medium,
            fontSize = 48.sp,   // 32px  Home -> "Aspen"
            lineHeight = 56.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = FontFamily(Font(resource = Res.font.Montserrat_SemiBold, weight = FontWeight.SemiBold)),
            fontWeight = FontWeight.SemiBold,
            fontSize = 34.sp,   // Popular, Recommended
            lineHeight = 36.sp,
            letterSpacing = 0.25.sp
        ),
        titleSmall = TextStyle(
            fontFamily = FontFamily(Font(resource = Res.font.Montserrat_SemiBold, weight = FontWeight.SemiBold)),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,      // Home -> Recommended -> Place Card Title
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,   // 14px : Place Details Page -> Description
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        labelMedium = TextStyle(
            color = AppColor.TextTertiary,
            fontFamily = FontFamily(Font(resource = Res.font.Montserrat_Regular, weight = FontWeight.Normal)),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,   // Home -> Place -> Place Card Title
            lineHeight = 18.sp,
            letterSpacing = 0.15.sp
        ),
        labelSmall = TextStyle(
            color = AppColor.TextTertiary,
            fontFamily = FontFamily(Font(resource = Res.font.Montserrat_Regular, weight = FontWeight.Normal)),
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,   // Rating/Review Label
            lineHeight = 16.sp,
            letterSpacing = 0.15.sp
        ),
    )
}
