package com.example.for_example

/**
 * Интерфейс для магазинов
 */
interface Store<out T : LibraryItem> {
    fun sell() : T
}