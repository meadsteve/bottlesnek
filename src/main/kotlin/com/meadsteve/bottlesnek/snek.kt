package com.meadsteve.bottlesnek

import io.javalin.Javalin
import java.util.*


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

    app.get("/move"){ ctx -> ctx.json(object {val move=randomMove(); val shout="What about second breakfast?"})}
}

fun getHerokuAssignedPort(): Int {
    val herokuPort = System.getenv("PORT")
    return herokuPort?.toInt() ?: 7000
}

fun randomMove(): Direction {
    return Direction.values().asList().shuffled().first()
}

enum class Direction {
    up, down, left, right;
}
