package org.katas

private const val MAX_AMOUNT_OF_PINS_RULE = 10

interface Game {

    /**
     * The argument is the number of pins knocked down.
     */
    fun roll(knockedPins: Int)

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

    override fun reset() {
        this.pins = MAX_AMOUNT_OF_PINS_RULE
    }

    fun availablePins(): Int {
        return this.pins
    }
}

