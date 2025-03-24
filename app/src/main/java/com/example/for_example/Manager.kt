package com.example.for_example

/**
 * Класс менеджера, реализует функцию покупки объектов
 */
class Manager {
    fun <T : LibraryItem> buy(store: Store<T>) : T {
        return store.sell()
    }
}