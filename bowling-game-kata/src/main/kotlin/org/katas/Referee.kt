package org.katas


interface Referee<T : Game> {
    fun game(): T
    fun next(): Player
    fun process(player: Player, frame: Frame)
    fun mustChangePlayer(): Boolean
    fun score(player: Player): Int
}

data class BowlingReferee constructor(
    private val players: MutableList<Player> = mutableListOf(),
    private val framesByPlayer: MutableMap<Player, Frame> = mutableMapOf(),
    private val game: Bowling,
) : Referee<Bowling> {

    override fun game(): Bowling {
        return this.game
    }

    override fun next(): Player {
        return players.takeIf { it.isNotEmpty() }
            ?.let {
                val nextPlayer = it.removeFirst()
                it.add(nextPlayer)
                nextPlayer
            } ?: throw NoSuchElementException("There are no registered players.")
    }

    override fun process(player: Player, frame: Frame) {
        if (this.framesByPlayer.containsKey(player)) {
            this.framesByPlayer[player]?.nextFrame = frame
        } else {
            this.framesByPlayer[player] = frame
        }
    }

    override fun mustChangePlayer(): Boolean {
        return game.availablePins() == 0
    }

    override fun score(player: Player): Int {
        val firstFrame =
            framesByPlayer[player] ?: throw IllegalArgumentException("No frames found for the specified player.")

        return BowlingScoreCalculator.calculate(firstFrame)
    }

    fun clear() {
        framesByPlayer.clear()
    }
}

//TODO test me!
object BowlingScoreCalculator {
    fun calculate(frame: Frame): Int {
        fun recursiveScore(currentFrame: Frame?): Int {
            return if (currentFrame != null) {
                currentFrame.score() + recursiveScore(currentFrame.nextFrame)
            } else {
                0
            }
        }

        return recursiveScore(frame)
    }
}