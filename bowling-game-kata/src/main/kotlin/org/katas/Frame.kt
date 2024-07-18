package org.katas

data class Frame(
    var firstRoll: Int,
    var secondRoll: Int? = null,
    var nextFrame: Frame? = null
) {
    private fun baseScore(): Int = firstRoll + (secondRoll ?: 0)

    fun score(): Int {
        var score = baseScore()
        if (BowlingScore.Strike.match(this)) {
            score += strikeBonus()
        } else if (BowlingScore.Spare.match(this)) {
            score += spareBonus()
        }
        return score
    }

    private fun strikeBonus(): Int = nextFrame?.firstRoll?.let { first ->
        nextFrame?.secondRoll?.let { second -> first + second } ?: nextFrame?.nextFrame?.firstRoll ?: 0
    } ?: 0

    private fun spareBonus(): Int = nextFrame?.firstRoll ?: 0
}

