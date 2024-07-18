package org.katas

import kotlin.random.Random

interface GameExecutor {
    fun executeRoll(player: Player)
}

class BowlingExecutor(
    private val game: Game,
    private val referee: Referee<Bowling>
) : GameExecutor {
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