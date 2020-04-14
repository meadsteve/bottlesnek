package com.meadsteve.bottlesnek

import com.meadsteve.bottlesnek.domain.Game
import com.meadsteve.bottlesnek.strategies.idealMove
import io.javalin.Javalin
import org.slf4j.LoggerFactory


val logger = LoggerFactory.getLogger("bottlesnek")

fun main(_args: Array<String>) {
    val app = Javalin.create().start(getHerokuAssignedPort())
    val snakeConfig = object {
        val color = "#e34234"
        val headType = "silly"
        val tailType = "bolt"
    }

    app.get("/") { ctx -> ctx.result("Hello snek") }

    app.get("/ping") { ctx -> ctx.result("pong") }
    app.post("/ping") { ctx -> ctx.result("pong") }

    app.post("/start") { ctx -> ctx.json(snakeConfig)}
    app.post("/end") { ctx -> ctx.json("ok")}

    app.post("/move"){ ctx ->
        val game = ctx.body<Game>()
        logger.info("Got a board to make a move on. $game", game)
        val move = idealMove(game).value
        logger.info("Making move: $move", move)
        ctx.json(mapOf("move" to move))
    }

    logger.info("snnnnnn")
}

fun getHerokuAssignedPort(): Int {
    val herokuPort = System.getenv("PORT")
    return herokuPort?.toInt() ?: 7000
}