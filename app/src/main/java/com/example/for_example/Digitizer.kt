package com.example.for_example

/**
 * Интерфейс для разных оцифровщиков
 */
interface Digitizer<out Output : DigitalMedium> {
    fun digitize(item: LibraryItem) : Output
}