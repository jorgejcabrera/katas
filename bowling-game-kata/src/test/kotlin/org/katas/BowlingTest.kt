package org.katas

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe

class BowlingGameTest : BehaviorSpec({

    Given("A bowling game with two players") {
        val jorge = Player("Jorge")
        val flor = Player("Florencia")
        val match = BowlingMatch.Builder()
            .registry(jorge)
            .registry(flor)
            .start()
        val referee = match.referee()

        afterEach {
            (referee as BowlingReferee).clear()
        }

        When("the referee assign a turn and the player play") {
            val player = referee.next()
            player.play()

            Then("the game pins must be reset") {
                match.game().availablePins() shouldBe 10
            }
        }

        When("the both players play") {
            repeat(4) {
                val player = referee.next()
                player.play()
            }

            Then("the game pins must be reset and the frames must be initialized") {
                match.game().availablePins() shouldBe 10
                match.referee().score(jorge) shouldBeGreaterThanOrEqual 0
                match.referee().score(flor) shouldBeGreaterThanOrEqual 0
            }
        }
    }

})
