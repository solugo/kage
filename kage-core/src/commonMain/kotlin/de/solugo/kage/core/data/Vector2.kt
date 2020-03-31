package de.solugo.kage.core.data

class Vector2 private constructor(val x: Float, val y: Float) {
    companion object {
        val Zero = Vector2(0f, 0f)
        val Identity = Vector2(0f, 0f)

        operator fun invoke(x: Float, y: Float) = when {
            x == Zero.x && y == Zero.y -> Zero
            x == Identity.x && y == Identity.y -> Identity
            else -> Vector2(x, y)
        }
    }

    operator fun plus(other: Vector2) = when {
        this == Zero -> other
        other == Zero -> this
        else -> invoke(x + other.x, y + other.y)
    }

    operator fun minus(other: Vector2) = when {
        other == Zero -> this
        else -> invoke(x - other.x, y - other.y)
    }

    operator fun times(factor: Float) = when {
        this == Zero -> Zero
        factor == 1f -> this
        factor == 0f -> Zero
        else -> invoke(x * factor, y * factor)
    }

    operator fun times(other: Vector2) = when {
        this == Zero -> Zero
        this == Identity -> other
        other == Zero -> Zero
        other == Identity -> this
        else -> invoke(x * other.x, y * other.y)
    }

    operator fun div(factor: Float) = when {
        factor == 0f -> throw RuntimeException("Division by zero")
        this == Zero -> Zero
        factor == 1f -> this
        else -> invoke(x * factor, y * factor)
    }

    operator fun div(other: Vector2) = when {
        other == Zero || other.x == 0f || other.y == 0f -> throw RuntimeException("Division by zero")
        this == Zero -> Zero
        other == Identity -> this
        else -> invoke(x * other.x, y * other.y)
    }
}