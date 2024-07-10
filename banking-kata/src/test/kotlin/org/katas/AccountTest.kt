package org.katas

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.lang.RuntimeException

class AccountTest : BehaviorSpec({

    var account = Account()

    afterEach {
        account = Account()
    }

    Given("an empty back account") {

        When("someone deposit a specific amount of money") {
            account.deposit(100)

            Then("the balance should be the expected") {
                account.balance() shouldBe 100
            }
        }

        When("someone deposit 1000 usd and then withdraw just 500") {
            account.deposit(1000)
            account.withdraw(500)

            Then("the balance should be the expected") {
                account.balance() shouldBe 500
            }
        }

        When("someone deposit 1000 usd") {
            account.deposit(1000)


            Then("when we withdraw 1500 it must fail") {
                val ex = shouldThrow<RuntimeException> {
                    account.withdraw(1500)
                }
                ex.message shouldBe "There isn't enough money. Please try with a low amount."
            }
        }

        When("someone try to withdraw  -1000 usd") {
            Then("it must fail") {
                val ex = shouldThrow<RuntimeException> {
                    account.withdraw(-1000)
                }
                ex.message shouldBe "Withdrawal amount must be positive."
            }
        }
    }

})
