package io.spine.graal

public fun main() {
    val app = createApp()
    Login(app).start()
    app.startMentionsFlow()
        .allMentions()
        .forEach {
            println(it)
        }
}
