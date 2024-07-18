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

interface FrameScore {
    fun match(frame: Frame): Boolean
}

sealed class BowlingScore : FrameScore {

    object Strike : BowlingScore() {
        override fun match(frame: Frame): Boolean {
            return frame.firstRoll == MAX_AMOUNT_OF_PINS_RULE
        }
    }

    object Spare : BowlingScore() {
        override fun match(frame: Frame): Boolean {
            return frame.firstRoll < MAX_AMOUNT_OF_PINS_RULE && frame.secondRoll != null && (frame.firstRoll + frame.secondRoll!! == MAX_AMOUNT_OF_PINS_RULE)
        }

    }
}
