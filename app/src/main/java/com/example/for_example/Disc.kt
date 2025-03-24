package com.example.for_example

/**
 * Предоставляет тип "Диск" для библиотеки и реализует для этого типа функции родителя
 * @param typeOfDisc Тип диска
 */
class Disc(
    id: Int,
    isAvailable: Boolean,
    title: String,
    type: String,
    private val typeOfDisc: String
) : LibraryItem(id, isAvailable, title, type), HomeTakeble, DigitalMedium {
    override fun getDetailInfo() : String {
        return "$type \"$title\" доступен: ${if (isAvailable) "Да" else "Нет"}."
    }

    override fun takeHome() {
        if (isAvailable) {
            println("$typeOfDisc диск: \"$title\" с id: $id взят домой.")
            isAvailable = !isAvailable
        } else
            println("Невозможно взять домой диск: \"$title\" с id: $id. Причина: диск недоступен.")
    }
}