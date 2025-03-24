package com.example.for_example

/**
 * Реализует книжный магазин для менеджера
 */
class BookStore : Store<Book> {
    override fun sell(): Book = Book(99002, true, "SomeBook", "Книга", 1986, "SomeAuthor")
}