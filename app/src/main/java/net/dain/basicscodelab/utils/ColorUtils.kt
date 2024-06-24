package net.dain.basicscodelab.utils

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils
import kotlin.math.min


fun Color.solid(): Color {
    return Color(ColorUtils.setAlphaComponent(this.toArgb(), 255))
}

fun Color.contrastWith(other: Color): Double {
    val solidBG = other.solid()
    return ColorUtils.calculateContrast(this.toArgb(), solidBG.toArgb())
}
fun Color.mixWith(other: Color, ratio: Float): Color {
    return Color(ColorUtils.blendARGB(
        this.toArgb(),
        other.toArgb(),
        ratio
    ))
}
fun Color.add(other: Color, colorPercent: Float = 1.0f): Color {
    return Color(
        red = min(red + (other.red * colorPercent), 1.0f),
        green = min(green + (other.green * colorPercent), 1.0f),
        blue = min(blue + (other.blue * colorPercent), 1.0f),
        alpha = min(alpha + (other.alpha * colorPercent), 1.0f)
    )
}

fun Color.darken(ratio: Float = 0.004f): Color {
    return this.add(Color.Black, ratio)
}
fun Color.lighten(ratio: Float = -0.004f): Color {
    return this.add(Color.White, ratio)
}



fun Color.contrast(
    bgColor: Color = Color.Unspecified,
    minContrast: Float = 0.5f,
    blackOrWhite: Boolean = false
): Color {
    if (this.isUnspecified) {
        return Color.Unspecified
    }

    if (blackOrWhite) {
        val luminance = this.luminance()
        return if (luminance > 0.5) Color.Black else Color.White
    }

    if (bgColor.isSpecified) {
        var newColor = this
        var contrast = this.contrastWith(bgColor)
        Log.i("ColorUtils", "Contrast: $contrast")

        while (contrast < minContrast) {

            val newLuminance = newColor.luminance()
            val baseLuminance = bgColor.luminance()

            newColor = if (newLuminance > baseLuminance) {
                newColor.darken()
            }
            else {
                newColor.lighten()
            }

            contrast = newColor.contrastWith(bgColor)
            Log.i("ColorUtils", "Contrast: $contrast")
        }

        return newColor
    }
    else {
        var newColor = this.copy()
        var contrast: Double

        do {
            val newLuminance = newColor.luminance()
            val shouldDarken = newLuminance > 0.5

            newColor = if (shouldDarken) newColor.darken(0.004f) else newColor.lighten(0.004f)


            contrast = newColor.contrastWith(this)
        } while (contrast < minContrast)

        return newColor
    }


}