package org.katas

import java.time.LocalDate

/**
 * Represents a movement in an account, with a date and a balance change.
 */
interface Movements {
    val date: LocalDate
    fun balanceChange(): Int
}

/**
 * Represents a financial transaction, which can be a deposit or a withdrawal.
 * The date defaults to the current date.
 */
sealed class Transaction(val amount: Int, override val date: LocalDate = LocalDate.now()) : Movements {
    /**
     * Represents a deposit transaction.
     */
    class Deposit(amount: Int) : Transaction(amount = amount) {
        override fun balanceChange(): Int {
            return this.amount
        }
    }

    /**
     * Represents a withdrawal transaction.
     * The amount must be positive.
     */
    class Withdrawal(amount: Int) : Transaction(amount = amount) {
        init {
            require(amount > 0) { "Withdrawal amount must be positive." }
        }

        override fun balanceChange(): Int {
            return -this.amount
        }
    }
}

/**
 * Holds the details of a transaction and the resulting balance after the transaction.
 */
data class LedgerEntry(
    val transaction: Transaction,
    val balance: Int,
) {
    fun printStatement() {
        println("${transaction.date}\t\t${balanceChangeToPrint()}\t\t$balance")
    }

    private fun balanceChangeToPrint(): String {
        return if (this.transaction.balanceChange() > 0) {
            "+${this.transaction.balanceChange()}"
        } else {
            this.transaction.balanceChange().toString()
        }
    }
}

/**
 * Represents a bank account, holding a list of transactions and a transaction history.
 */
class Account(
    private var transactions: MutableList<Transaction> = mutableListOf(),
    private var ledger: MutableList<LedgerEntry> = mutableListOf()
) {
    fun deposit(amount: Int) {
        val tx = Transaction.Deposit(amount)
        this.transactions.add(tx).also {
            this.ledger.add(LedgerEntry(transaction = tx, balance = this.balance()))
        }
    }

    fun balance(): Int {
        return this.transactions.sumOf { it.balanceChange() }
    }

    fun printStatement() {
        println("Date\t\t\tAmount\t\tBalance")
        for (transaction in this.ledger) {
            transaction.printStatement()
        }
    }

    fun withdraw(amount: Int) {
        if (amount > balance()) {
            throw RuntimeException("There isn't enough money. Please try with a low amount.")
        }
        val tx = Transaction.Withdrawal(amount = amount)
        this.transactions.add(tx).also {
            this.ledger.add(LedgerEntry(transaction = tx, balance = this.balance()))
        }
    }
}