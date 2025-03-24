package com.example.for_example

/**
 * Интерфейс всех объектов, которые можно брать в зал
 */
interface HomeTakeble {
    /**
     * Проверяет возможно ли взятие объекта домой и меняет состояние доступности объекта
     */
    fun takeHome()
}