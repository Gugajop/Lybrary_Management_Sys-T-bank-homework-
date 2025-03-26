package com.example.for_example

/**
 * Реализует оцифровку объектов в CD диск
 */
class CDDigitizer : Digitizer<Disc> {
    override fun digitize(item: LibraryItem) : Disc {
        return Disc(
            id = idCounter++,
            title = "Цифровая копия. ${item.type}. \"${item.title}\"",
            isAvailable = true,
            type = "Диск",
            typeOfDisc = "CD"
        )
    }
}