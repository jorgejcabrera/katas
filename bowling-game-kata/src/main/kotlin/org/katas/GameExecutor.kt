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
        val availablePins = referee.game().availablePins()
        val firstKnockedPins = Random.nextInt(0, availablePins + 1)
        game.roll(firstKnockedPins)

        val secondKnockedPins = if (!referee.mustChangePlayer()) {
            Random.nextInt(0, referee.game().availablePins() + 1).also { game.roll(it) }
        } else null

        Frame(firstRoll = firstKnockedPins, secondRoll = secondKnockedPins).also { frame ->
            referee.process(player, frame)
            game.reset()
        }
    }
}