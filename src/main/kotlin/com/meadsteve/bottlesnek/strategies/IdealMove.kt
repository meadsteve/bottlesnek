package com.meadsteve.bottlesnek.strategies

import com.meadsteve.bottlesnek.domain.Game
import com.meadsteve.bottlesnek.logger
import com.meadsteve.bottlesnek.space.*

fun idealMove(game: Game): Direction {
    logger.info("Trying to find ideal move")
    val foodDirection = lookForFoodStrategy(game)
    if (foodDirection != null) return foodDirection
    logger.info("Ran out of strategies so moving randomly")
    return randomDirection()
}

private fun lookForFoodStrategy(game: Game): Direction? {
    val snakesHead = game.you.head
    val occupiedSpaces: List<GridPoint> = game.board.snakes.flatMap { it.body }.map { Square(it.x, it.y) }.distinct()

    for (food in game.board.food) {
        val heading = findHeading(from = snakesHead, to = food)
        if (snakesHead + heading !in occupiedSpaces) {
            logger.info("Heading $heading towards some food")
            return heading
        }
    }
    return null
}

private operator fun GridPoint.plus(direction: Direction): Square {
    return Square(
        this.x + direction.vector.x, this.y + direction.vector.y
    )
}
