package de.solugo.nerus.core.data

class Color private constructor(val r: Float, val g: Float, val b: Float, val a: Float) {
    companion object {
        val Transparent = Color(0f, 0f, 0f, 0f)
        val Black = Color(0f, 0f, 0f, 0f)

        operator fun invoke(r: Float, g: Float, b: Float, a: Float = 1f) = when {
            r == Transparent.r && g == Transparent.g && b == Transparent.b && a == Transparent.a -> Transparent
            r == Black.r && g == Black.g && b == Black.b && a == Black.a -> Black
            else -> Color(r, g, b, a)
        }
    }
}