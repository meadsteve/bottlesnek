package com.meadsteve.bottlesnek.space

interface Square{
    val x: Int
    val y: Int
}

fun randomDirection() = Direction.values().asList().shuffled().first()
enum class Direction(val value: String) {
    Up("up"),
    Down("down"),
    Left("left"),
    Right("right");
}

fun findHeading(from: Square, to: Square): Direction {
    return when {
        from.y == to.y && from.x > to.x -> Direction.Left
        from.y == to.y && from.x < to.x -> Direction.Right
        from.x == to.x && from.y > to.y -> Direction.Down
        from.x == to.x && from.y < to.y -> Direction.Up
        else -> randomDirection()
    }
}