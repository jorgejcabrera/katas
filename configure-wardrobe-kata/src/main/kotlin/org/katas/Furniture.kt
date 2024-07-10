package org.katas

data class Element(
    val sizeInCentimeters: Int,
    val cost: Double
)

data class Furniture(
    val elements: MutableList<Element> = mutableListOf()
) {
    fun sizeInCentimeters(): Int {
        return this.elements.sumOf { it.sizeInCentimeters }
    }

    fun price(): Double {
        return this.elements.sumOf { it.cost }
    }
}