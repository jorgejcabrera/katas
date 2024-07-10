package org.katas

interface WardrobeCustomizer {
    /**
     * combine will return a list of elements which can fill the wall capacity
     */
    fun combine(elements: List<Element>, wallCapacity: Int): List<Furniture>

    fun chipest(elements: List<Element>, wallCapacity: Int): Furniture
}

class SwedishCustomizer : WardrobeCustomizer {
    override fun combine(elements: List<Element>, wallCapacity: Int): List<Furniture> {
        val furnitures = mutableListOf<Furniture>()
        backtrack(elements, wallCapacity, 0, mutableListOf(), furnitures)
        return furnitures
    }

    override fun chipest(elements: List<Element>, wallCapacity: Int): Furniture {
        return combine(elements, wallCapacity).minBy { it.price() }
    }

    private fun backtrack(
        elements: List<Element>,
        capacity: Int,
        index: Int,
        current: MutableList<Element>,
        furnitures: MutableList<Furniture>
    ) {
        if (capacity == 0) {
            furnitures.add(Furniture(current.toMutableList()))
            return
        }
        if (capacity < 0) {
            return
        }
        for (i in index until elements.size) {
            current.add(elements[i])
            backtrack(elements, capacity - elements[i].sizeInCentimeters, i, current, furnitures)
            current.removeLast()
        }
    }
}