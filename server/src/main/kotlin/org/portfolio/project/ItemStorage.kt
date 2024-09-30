package org.portfolio.project

object ItemStorage {
    private val items = mutableListOf<Item>()
    private var currentId = 1

    fun getAll(): List<Item> = items.toList()

    fun getById(id: Int): Item? = items.find { it.id == id }

    fun add(item: Item): Item {
        val newItem = item.copy(id = currentId++)
        items.add(newItem)
        return newItem
    }

    fun update(id: Int, updatedItem: Item): Boolean {
        val index = items.indexOfFirst { it.id == id }
        return if (index != -1) {
            items[index] = updatedItem.copy(id = id)
            true
        } else {
            false
        }
    }

    fun delete(id: Int): Boolean = items.removeIf { it.id == id }
}
