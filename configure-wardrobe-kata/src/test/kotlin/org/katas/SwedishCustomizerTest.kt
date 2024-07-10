package org.katas

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class WardrobeCustomizerTest : BehaviorSpec({
    val customizer = SwedishCustomizer()
    Given("a list of elements and a wall capacity") {
        val elements = listOf(
            Element(sizeInCentimeters = 50, cost = 59.0),
            Element(sizeInCentimeters = 75, cost = 62.0),
            Element(sizeInCentimeters = 100, cost = 90.0),
            Element(sizeInCentimeters = 120, cost = 111.0),
        )
        val wallCapacity = 250

        When("customize your own furniture") {
            val furniture = customizer.combine(elements, wallCapacity)
            /**
             * the possible combinations are:
             * - 5 x 50
             * - 2 x 75, 1 x 100
             * - 3 x 50, 1 x 100
             * - 2 x 100, 1 x 50
             * - 2 x 75, 2 x 50
             */
            Then("all the possible furniture must be offered") {
                furniture.size shouldBe 5
            }
        }
        When("look for the chipest furniture") {
            val furniture = customizer.chipest(elements, wallCapacity)
            /**
             * the possible combinations are:
             * - 5 x 50 -> 295
             * - 2 x 75, 1 x 100 -> 214
             * - 3 x 50, 1 x 100 -> 240
             * - 2 x 100, 1 x 50 -> 239
             * - 2 x 75, 2 x 50 -> 242
             */
            Then("all the possible furniture must be offered") {
                furniture.price() shouldBe 214.0
            }
        }
    }
})
