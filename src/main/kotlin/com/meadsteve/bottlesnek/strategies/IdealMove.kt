package com.meadsteve.bottlesnek.strategies

import com.meadsteve.bottlesnek.domain.Game
import com.meadsteve.bottlesnek.logger
import com.meadsteve.bottlesnek.space.Direction
import com.meadsteve.bottlesnek.space.findHeading
import com.meadsteve.bottlesnek.space.randomDirection

fun idealMove(game: Game): Direction {
    return Direction.Left
    logger.info("Trying to find ideal move")
    if(game.board.food.isEmpty()) {
        logger.info("Empty board so I'm moving at random")
        return randomDirection()
    }
    val firstPieceOfFood = game.board.food.first()
    val heading =
        findHeading(from = game.you.head, to = firstPieceOfFood)
    logger.info("Heading $heading to the food")
    return heading
}