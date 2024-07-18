package org.katas

data class Frame(
    var firstRoll: Int,
    var secondRoll: Int? = null,
    var nextFrame: Frame? = null
) {
    private fun baseScore(): Int = firstRoll + (secondRoll ?: 0)

    fun score(): Int {
        var score = baseScore()
        if (BowlingSpecialScore.Strike.match(this)) {
            score += BowlingSpecialScore.Strike.bonus(this)
        } else if (BowlingSpecialScore.Spare.match(this)) {
            score += BowlingSpecialScore.Spare.bonus(this)
        }
        return score
    }
}

