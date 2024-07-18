package org.katas

data class Player(
    val name: String,
    internal var gameExecutor: GameExecutor? = null
) {

    fun play() {
        gameExecutor?.executeRoll(this)
    }
}