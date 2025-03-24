package com.example.for_example

/**
 * Интерфейс всех объектов, которые можно брать в зал
 * */
interface LibraryReadble {
    /**
     * Проверяет возможно ли взятие объекта в зал и меняет состояние доступности объекта
     */
    fun takeToRead()
}