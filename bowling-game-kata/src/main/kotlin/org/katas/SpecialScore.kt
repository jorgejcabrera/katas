package org.katas

interface SpecialScore {
    fun match(frame: Frame): Boolean
    fun bonus(frame: Frame): Int
}

sealed class BowlingSpecialScore : SpecialScore {

    object Strike : BowlingSpecialScore() {
        override fun match(frame: Frame): Boolean {
            return frame.firstRoll == 10
        }

        override fun bonus(frame: Frame): Int {
            return frame.nextFrame?.firstRoll?.let { first ->
                frame.nextFrame?.secondRoll?.let { second -> first + second } ?: frame.nextFrame?.nextFrame?.firstRoll ?: 0
            } ?: 0
        }
    }

    object Spare : BowlingSpecialScore() {
        override fun match(frame: Frame): Boolean {
            return frame.firstRoll < 10 && frame.secondRoll != null && (frame.firstRoll + frame.secondRoll!! == 10)
        }

        override fun bonus(frame: Frame): Int {
            return frame.nextFrame?.firstRoll ?: 0
        }
    }
}
