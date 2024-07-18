package org.katas

data class Frame(
    var firstRoll: Int,
    var secondRoll: Int? = null,
    var nextFrame: Frame? = null
)

interface Referee<T : Game> {
    fun game(): T
    fun next(): Player
    fun process(player: Player, frame: Frame)
    fun mustChangePlayer(): Boolean
    fun score(player: Player): Int
}

data class BowlingReferee constructor(
    private val players: MutableList<Player> = mutableListOf(),
    private val framesByPlayer: MutableMap<Player, MutableList<Frame>> = mutableMapOf(),
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
            this.framesByPlayer[player]?.last()?.nextFrame = frame
        } else {
            this.framesByPlayer[player] = mutableListOf(frame)
        }
    }

    override fun mustChangePlayer(): Boolean {
        return game.availablePins() == 0
    }

    // TODO FIX ME!
    override fun score(player: Player): Int {
        return framesByPlayer.getOrDefault(player, mutableListOf())
            .sumOf { frame -> frame.firstRoll }
    }

    fun clear() {
        framesByPlayer.clear()
    }
}