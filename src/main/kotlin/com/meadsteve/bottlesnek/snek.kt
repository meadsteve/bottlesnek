package com.meadsteve.bottlesnek

import io.javalin.Javalin


fun main(_args: Array<String>) {
    val app = Javalin.create().start(getHerokuAssignedPort())
    val snakeConfig = object {
        val color = "#e34234"
        val headType = "silly"
        val tailType = "pixel"
    }

    app.get("/") { ctx -> ctx.result("Hello snek") }
    app.get("/ping") { ctx -> ctx.result("pong") }

    app.post("/start") { ctx -> ctx.json(snakeConfig)}
    app.post("/end") { ctx -> ctx.json("ok")}

    app.post("/move"){ ctx ->
        val game = ctx.body<Game>()
        ctx.json(idealMove(game))
    }
}

fun idealMove(game: Game): Move {
    if(game.board.food.isEmpty()) {
        return Move(randomDirection(), "THERE'S NO FOOD")
    }
    val firstPieceOfFood = game.board.food.first()
    val heading = findHeading(from=game.you.head, to = firstPieceOfFood)
    return Move(heading, "NOM NOM NOM PENDING")
}

fun getHerokuAssignedPort(): Int {
    val herokuPort = System.getenv("PORT")
    return herokuPort?.toInt() ?: 7000
}

data class Move(val move: Direction, val shout: String="HELPING!")

interface Square{
    val x: Int
    val y: Int
}

data class BodyPiece(override val x: Int, override val y: Int): Square
data class Food(override val x: Int, override val y: Int): Square

data class Snake(val id: String, val name: String, val health: String, val body: List<BodyPiece>, val shout: String) {
    val head: BodyPiece
        get() = this.body.first()
}

data class Board(val height: Int, val width: Int, val food: List<Food>, val snakes: List<Snake>)

data class Game(val game: Any?, val turn: Int, val board: Board, val you: Snake)


fun randomDirection() = Direction.values().asList().shuffled().first()

enum class Direction {
    up, down, left, right;
}

fun findHeading(from: Square, to: Square): Direction {
    return when {
        from.y == to.y && from.x > to.x -> Direction.left
        from.y == to.y && from.x < to.x -> Direction.right
        from.x == to.x && from.y > to.y -> Direction.down
        from.x == to.x && from.y < to.y -> Direction.up
        else -> randomDirection()
    }
}