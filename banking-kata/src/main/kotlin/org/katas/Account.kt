package org.katas

import java.time.LocalDate

interface Movements {
    fun balanceChange(): Int
}

sealed class Transaction(val amount: Int, val date: LocalDate = LocalDate.now()) : Movements {
    class Deposit(amount: Int) : Transaction(amount = amount) {
        override fun balanceChange(): Int {
            return this.amount
        }
    }

    class Withdrawal(amount: Int) : Transaction(amount = amount) {
        init {
            require(amount > 0) { "Withdrawal amount must be positive." }
        }
        override fun balanceChange(): Int {
            return -this.amount
        }
    }
}

class Account(
    private var transactions: MutableList<Transaction> = mutableListOf(),
) {
    fun deposit(amount: Int) {
        this.transactions.add(Transaction.Deposit(amount))
    }

    fun balance(): Int {
        return this.transactions.sumOf { it.balanceChange() }
    }

    fun withdraw(amount: Int) {
        if (amount > balance()) {
            throw RuntimeException("There isn't enough money. Please try with a low amount.")
        }
        this.transactions.add(Transaction.Withdrawal(amount = amount))
    }
}