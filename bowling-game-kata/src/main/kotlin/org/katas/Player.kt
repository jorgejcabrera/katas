package org.katas

data class Player(
    val name: String,
    internal var controller: Controller? = null
) {

    fun play() {
        controller?.executeRoll(this)
    }
}