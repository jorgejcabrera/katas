package org.katas

interface Match<T : Game> {
    fun game(): T
    fun referee(): Referee<T>
    fun players(): List<Player>
}

class BowlingMatch(
    private val players: MutableList<Player> = mutableListOf(),
    private val referee: Referee<Bowling>,
    private val game: Bowling
) : Match<Bowling> {
    override fun game(): Bowling {
        return this.game
    }

    override fun referee(): Referee<Bowling> {
        return this.referee
    }

    override fun players(): List<Player> {
        return this.players
    }

    data class Builder(
        private val players: MutableList<Player> = mutableListOf(),
        private val referee: Referee<Bowling>? = null,
        private val game: Game? = null
    ) {

        fun registry(player: Player): Builder {
            return this.apply { this.players.add(player) }
        }

        fun start(): BowlingMatch {
            val game = Bowling()
            val referee = BowlingReferee(players = this.players, game = game)
            val executor = BowlingExecutor(game = game, referee = referee)
            this.players.forEach {
                it.gameExecutor = executor
            }
            return BowlingMatch(players, referee, game)
        }
    }

}