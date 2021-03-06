package com.meadsteve.bottlesnek.domain

import com.meadsteve.bottlesnek.space.GridPoint

data class BodyPiece(override val x: Int, override val y: Int): GridPoint
data class Food(override val x: Int, override val y: Int): GridPoint
data class Snake(val id: String, val name: String, val health: String, val body: List<BodyPiece>, val shout: String) {
    val head: BodyPiece
        get() = this.body.first()
}

data class Board(val height: Int, val width: Int, val food: List<Food>, val snakes: List<Snake>)
data class Game(val game: Any?, val turn: Int, val board: Board, val you: Snake)