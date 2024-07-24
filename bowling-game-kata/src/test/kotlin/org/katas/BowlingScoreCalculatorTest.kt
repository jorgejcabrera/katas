package org.katas

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BowlingScoreCalculatorTest : BehaviorSpec({


    Given("a bowling calculator") {
        val calculator = BowlingScoreCalculator

        When("there is a list of frames") {
            val frames = Frame(
                firstRoll = 4,
                secondRoll = 4,
                nextFrame = Frame(
                    firstRoll = 9,
                    secondRoll = 0,
                    nextFrame = Frame(
                        firstRoll = 0,
                        secondRoll = 9
                    )
                )
            )
            val result = calculator.calculate(frames)

            Then("the result should be the expected") {
                result shouldBe 26
            }
        }
        When("there is a list of with an spare") {
            val frames = Frame(
                firstRoll = 4,
                secondRoll = 4,
                nextFrame = Frame(
                    firstRoll = 9,
                    secondRoll = 1,
                    nextFrame = Frame(
                        firstRoll = 4,
                        secondRoll = 2
                    )
                )
            )
            val result = calculator.calculate(frames)

            Then("the result should be the expected") {
                result shouldBe 28
            }
        }
        When("there is a list of frames with an spare at the beginning") {
            val frames = Frame(
                firstRoll = 4,
                secondRoll = 6,
                nextFrame = Frame(
                    firstRoll = 9,
                    secondRoll = 0,
                    nextFrame = Frame(
                        firstRoll = 4,
                        secondRoll = 2
                    )
                )
            )
            val result = calculator.calculate(frames)

            Then("the result should be the expected") {
                result shouldBe 34
            }
        }
        When("there is a list frames of with two consecutive spare") {
            val frames = Frame(
                firstRoll = 4,
                secondRoll = 6,
                nextFrame = Frame(
                    firstRoll = 9,
                    secondRoll = 1,
                    nextFrame = Frame(
                        firstRoll = 4,
                        secondRoll = 2
                    )
                )
            )
            val result = calculator.calculate(frames)

            Then("the result should be the expected") {
                result shouldBe 39
            }
        }
        When("there is a list frames of an strike at the beginning") {
            val frames = Frame(
                firstRoll = 10,
                secondRoll = 0,
                nextFrame = Frame(
                    firstRoll = 9,
                    secondRoll = 1,
                    nextFrame = Frame(
                        firstRoll = 4,
                        secondRoll = 2
                    )
                )
            )
            val result = calculator.calculate(frames)

            Then("the result should be the expected") {
                result shouldBe 40
            }
        }
    }
})
