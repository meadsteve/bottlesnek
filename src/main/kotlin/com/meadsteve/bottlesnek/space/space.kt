package com.meadsteve.bottlesnek.space

data class Move(val move: Direction, val shout: String="HELPING!")
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
        from.y == to.y && from.x > to.x -> Direction.left
        from.y == to.y && from.x < to.x -> Direction.right
        from.x == to.x && from.y < to.y -> Direction.down
        from.x == to.x && from.y > to.y -> Direction.up
        else -> randomDirection()
    }
}