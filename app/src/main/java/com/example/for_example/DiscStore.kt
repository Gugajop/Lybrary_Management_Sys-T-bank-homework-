package com.example.for_example

/**
 * Реализует магазин дисков для менеджера
 */
class DiscStore : Store<Disc> {
    override fun sell() : Disc = Disc(99003, true, "SomeDisc", "Диск", "CD")
}