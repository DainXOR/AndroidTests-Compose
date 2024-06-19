package net.dain.basicscodelab.utils

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

class Gradient(
    var start: Color,
    var end: Color,
    var forceAlpha: Float = 0f,
    var steps: UInt = 0u,
) : Collection<Color>
{
    private val colors = mutableListOf<Color>()

    init {
        regenerate()
    }

    override fun iterator(): Iterator<Color> {
        return colors.iterator()
    }
    override fun isEmpty(): Boolean {
        return colors.isEmpty()
    }
    override fun containsAll(elements: Collection<Color>): Boolean {
        return colors.containsAll(elements)
    }
    override fun contains(element: Color): Boolean {
        return colors.contains(element)
    }

    operator fun get(step: Int): Color {
        return colors[step]
    }

    override val size: Int
        get() = colors.size

    fun colors(): List<Color> {
        return colors
    }

    fun copy(): Gradient {
        return Gradient(start, end, forceAlpha, steps)
    }

    fun regenerate(): Boolean {
        if (steps == 0u) {
            return false
        }

        colors.clear()

        val beginColor = if(forceAlpha > 0f)
            Color(ColorUtils.setAlphaComponent(start.toArgb(), (forceAlpha * 255).toInt()))
        else
            start

        colors.add(beginColor)
        colors.addAll(generateGradient(start, end, steps - 1u, forceAlpha))
        return true
    }
    fun makeSolid(): Gradient {
        forceAlpha = 1f
        regenerate()
        return this
    }
    fun makeTransparent(alpha: Float): Gradient {
        forceAlpha = alpha
        regenerate()
        return this
    }
    fun usePaletteAlpha(): Gradient {
        forceAlpha = 0f
        regenerate()
        return this
    }

    private fun mix(c1: Color, c2: Color, ratio: Float = 0f, forceAlpha: Float = 0f): Color {
        val alphaValue = if (forceAlpha <= 0f) ((1f - ratio) * c1.alpha) + (ratio * c2.alpha) else forceAlpha

        Log.i("Gradient", "Alpha: $alphaValue")

        return Color(
            ((1f - ratio) * c1.red) + (ratio * c2.red),
            ((1f - ratio) * c1.green) + (ratio * c2.green),
            ((1f - ratio) * c1.blue) + (ratio * c2.blue),
            alphaValue
        )
    }
    private fun generateGradient(color1: Color, color2: Color, steps: UInt, forceAlpha: Float): List<Color> {
        val gradient = mutableListOf<Color>()
        for (i in 1..steps.toInt()) {
            gradient.add(mix(
                color1,
                color2,
                i.toFloat() / steps.toFloat(),
                forceAlpha
            ))
        }
        return gradient
    }
}
