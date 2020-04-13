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

    app.post("/move"){ ctx -> ctx.json(randomMove())}
}

fun getHerokuAssignedPort(): Int {
    val herokuPort = System.getenv("PORT")
    return herokuPort?.toInt() ?: 7000
}

data class Move(val move: Direction, val shout: String)

fun randomMove(): Move {
    return Move(
        move=Direction.values().asList().shuffled().first(),
        shout="What about second breakfast?"
    )
}

enum class Direction {
    up, down, left, right;
}
