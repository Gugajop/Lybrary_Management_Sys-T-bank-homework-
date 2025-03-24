package com.example.for_example

/**
 * Предоставляет тип "Книга" для библиотеки и реализует для этого типа функции родителя
 * @param pageCount Количество страниц в книге
 * @param author Имя автора книги
 */
class Book(
    id: Int,
    isAvailable: Boolean,
    title: String,
    type: String,
    private val pageCount: Int,
    private val author: String
): LibraryItem(id, isAvailable, title, type), HomeTakeble, LibraryReadble, Digitizable {
    override fun getDetailInfo() : String {
        return "книга: \"$title\" ($pageCount стр.) автора: $author с id: $id доступна ${if (isAvailable) "Да" else "Нет"}."
    }

    override fun takeHome() {
        if (isAvailable) {
            println("Книга: \"$title\" с id: $id взята домой.")
            isAvailable = !isAvailable
        } else
            println("Невозможно взять домой книгу: \"$title\" с id: $id. Причина: книга недоступна.")
    }

    override fun takeToRead() {
        if (isAvailable) {
            println("Книга: \"$title\" с id: $id взята в зал.")
            isAvailable = !isAvailable
        } else
            println("Невозможно взять на чтение книгу: \"$title\" с id: $id. Причина: книга недоступна.")
    }
}