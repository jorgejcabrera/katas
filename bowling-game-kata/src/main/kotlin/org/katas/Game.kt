package org.katas

private const val MAX_AMOUNT_OF_PINS_RULE = 10

interface Game {

    /**
     * The argument is the number of pins knocked down.
     */
    fun roll(knockedPins: Int)

    /**
     * Returns the total score for that game.
     */
    fun score(): Int

    /**
     * Reset all the changes after a player roll
     */
    fun reset()
}

class Bowling(
    private var pins: Int = MAX_AMOUNT_OF_PINS_RULE
) : Game {
    override fun roll(knockedPins: Int) {
        this.pins -= knockedPins
    }

    override fun score(): Int {
        TODO("Not yet implemented")
    }

    override fun reset() {
        this.pins = MAX_AMOUNT_OF_PINS_RULE
    }

    fun availablePins(): Int {
        return this.pins
    }
}

