package com.example.for_example

/**
* Является родительским классом для всех объектов, хранящихся в библиотеке
*
* @param id Идентификационный номер объекта
 * @param isAvailable Доступность объекта
 * @param title Название объекта
 * @param type Тип объекта
*/
abstract class LibraryItem (
    val id: Int,
    var isAvailable: Boolean,
    val title: String,
    val type : String
) {
    /**
     * Создает строку краткой информации об объекте
     * @return Строка с краткой информацией об объекте
     */
    fun getShortInfo() : String {
        return "\"$title\" доступно: ${if (isAvailable) "Да" else "Нет"}."
    }
    /**
     * Создает строку полной информации об объекте
     * @return Строка с полной информацией об объекте
     */
    abstract fun getDetailInfo() : String
    /**
     * Проверяет возможно ли вернуть объект в библиотеку и меняет состояние доступности объекта
     */
    fun bringBack() {
        if (!isAvailable) {
            println("Объект типа $type с названием: \"$title\" с id: $id возвращен.")
            isAvailable = !isAvailable
        } else
            println("Невозможно вернуть объект типа $type с названием: \"$title\" с id: $id. Причина: объект уже доступен пользователям.")
    }
}