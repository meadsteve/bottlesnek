package com.meadsteve.bottlesnek.space

data class Move(val move: Direction)
interface Square{
    val x: Int
    val y: Int
}

fun randomDirection() = Direction.values().asList().shuffled().first()
enum class Direction {
    up, down, left, right;
}

fun findHeading(from: Square, to: Square): Direction {
    return when {
        from.x == to.x && from.y > to.y -> Direction.left
        from.x == to.x && from.y < to.y -> Direction.right
        from.y == to.y && from.x < to.x -> Direction.down
        from.y == to.y && from.x > to.x -> Direction.up
        else -> randomDirection()
    }
}