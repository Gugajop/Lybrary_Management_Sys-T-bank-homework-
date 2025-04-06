package com.example.for_example

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources

/**
 * Предоставляет тип "Газета" для библиотеки и реализует для этого типа функции родителя
 * @param issueNumber Номер выпуска газеты
 * @param month Месяц выпуска газеты
 */
class Newspaper(
    id: Int,
    isAvailable: Boolean,
    title: String,
    type: String,
    private val issueNumber: Int,
    private val month: String
) : LibraryItem(id, isAvailable, title, type), LibraryReadble, Digitizable {
    override fun getDetailInfo() : String {
        return "выпуск: $issueNumber дата: $month газеты \"$title\" с id: $id доступен: ${if (isAvailable) "Да" else "Нет"}."
    }

    override fun takeToRead() {
        if (isAvailable) {
            println("Газета: \"$title\" с id: $id взята в зал.")
            isAvailable = !isAvailable
        } else  
            println("Невозможно взять на чтение газету: \"$title\" с id: $id. Причина: газета недоступна.")
    }

    override fun getIconResId(context: Context): Drawable? = AppCompatResources.getDrawable(context, R.drawable.ic_newspaper)
}