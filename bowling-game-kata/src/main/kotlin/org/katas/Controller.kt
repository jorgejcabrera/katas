package org.katas

import kotlin.random.Random

interface Controller {
    fun executeRoll(player: Player)
}

class BowlingController(
    private val game: Game,
    private val referee: Referee<Bowling>
) : Controller {
    override fun executeRoll(player: Player) {
        val firstKnockedPins = rollResult()
        game.roll(firstKnockedPins)

        val secondKnockedPins = if (!referee.mustChangePlayer()) {
            rollResult().also { game.roll(it) }
        } else null

        Frame(firstRoll = firstKnockedPins, secondRoll = secondKnockedPins).also { frame ->
            referee.process(player, frame)
            game.reset()
        }
    }

    private fun rollResult(): Int {
        val availablePins = referee.game().availablePins()
        return Random.nextInt(0, availablePins + 1)
    }
}