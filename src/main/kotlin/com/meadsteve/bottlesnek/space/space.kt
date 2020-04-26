package com.meadsteve.bottlesnek.space

interface GridPoint{
    val x: Int
    val y: Int
}
data class Square(override val x: Int, override val y: Int): GridPoint

data class Vector(val x: Int, val y: Int)

fun randomDirection() = Direction.values().asList().shuffled().first()

enum class Direction(val value: String, val vector: Vector) {
    Up("up", Vector(0, 1)),
    Down("down", Vector(0, -1)),
    Left("left", Vector(-1, 0)),
    Right("right", Vector(1, 0));
}

fun findHeading(from: GridPoint, to: GridPoint): Direction {
    return when {
        from.y > to.y -> Direction.Down
        from.y < to.y -> Direction.Up
        from.y == to.y && from.x > to.x -> Direction.Left
        from.y == to.y && from.x < to.x -> Direction.Right
        else -> randomDirection()
    }
}