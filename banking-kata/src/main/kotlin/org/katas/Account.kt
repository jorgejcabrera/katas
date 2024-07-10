package org.katas

import java.time.LocalDate

data class Transaction(
    val amount: Int,
    val date: LocalDate = LocalDate.now(),
    val type: Type
) {
    enum class Type {
        Deposit,
        Withdrawal
    }
}

class Account(
    private var transactions: MutableList<Transaction> = mutableListOf(),
) {
    fun deposit(amount: Int) {
        this.transactions.add(Transaction(amount = amount, type = Transaction.Type.Deposit))
    }

    fun balance(): Int {
        return this.transactions
            .filter { it.type == Transaction.Type.Deposit }
            .sumOf { it.amount } - this.transactions
            .filter { it.type == Transaction.Type.Withdrawal }
            .sumOf { it.amount }
    }

    fun withdraw(amount: Int) {
        if (amount > balance()) {
            throw RuntimeException("There isn't enough money. Please try with a low amount.")
        }
        this.transactions.add(Transaction(amount = amount, type = Transaction.Type.Withdrawal))
    }
}