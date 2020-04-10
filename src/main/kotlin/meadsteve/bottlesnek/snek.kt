package meadsteve.bottlesnek

import io.javalin.Javalin

fun main(_args: Array<String>) {
    val app = Javalin.create().start(getHerokuAssignedPort())
    app.get("/") { ctx -> ctx.result("Hello snek") }
}

fun getHerokuAssignedPort(): Int {
    val herokuPort = System.getenv("PORT")
    return herokuPort?.toInt() ?: 7000
}