package de.solugo.kage.core.data

class Vector3 private constructor(val x: Float, val y: Float, val z: Float) {
    companion object {
        val Zero = Vector3(0f, 0f, 0f)
        val Identity = Vector3(0f, 0f, 0f)

        operator fun invoke(x: Float, y: Float, z: Float) = when {
            x == Zero.x && y == Zero.y && z == Zero.z -> Zero
            x == Identity.x && y == Identity.y && z == Identity.z -> Identity
            else -> Vector3(x, y, z)
        }
    }

    operator fun plus(other: Vector3) = when {
        this == Zero -> other
        other == Zero -> this
        else -> invoke(
            x + other.x,
            y + other.y,
            z + other.z
        )
    }

    operator fun minus(other: Vector3) = when {
        other == Zero -> this
        else -> invoke(
            x - other.x,
            y - other.y,
            z - other.z
        )
    }

    operator fun times(factor: Float) = when {
        this == Zero -> Zero
        factor == 1f -> this
        factor == 0f -> Zero
        else -> invoke(x * factor, y * factor, z * factor)
    }

    operator fun times(other: Vector3) = when {
        this == Zero -> Zero
        this == Identity -> other
        other == Zero -> Zero
        other == Identity -> this
        else -> invoke(
            x * other.x,
            y * other.y,
            z * other.z
        )
    }

    operator fun div(factor: Float) = when {
        factor == 0f -> throw RuntimeException("Division by zero")
        this == Zero -> Zero
        factor == 1f -> this
        else -> invoke(x * factor, y * factor, z * factor)
    }

    operator fun div(other: Vector3) = when {
        other == Zero || other.x == 0f || other.y == 0f || other.z == 0f -> throw RuntimeException("Division by zero")
        this == Zero -> Zero
        other == Identity -> this
        else -> invoke(
            x * other.x,
            y * other.y,
            z * other.z
        )
    }
}