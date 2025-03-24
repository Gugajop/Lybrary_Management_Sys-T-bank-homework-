package com.example.for_example

/**
 * Реализует газетный ларек для менеджера
 */
class NewspaperKiosk : Store<Newspaper> {
    override fun sell() : Newspaper =
        Newspaper(99004, true, "SomeNewspaper", "Газета", 5148, "Март")
}