package com.example.for_example

fun main() {

    val libraryItems = mutableListOf(
        // Книги
        Book(90743, true, "Маугли", "Книга", 202, "Джозеф Киплинг"),
        Book(12345, true, "Война и мир", "Книга", 1225, "Лев Толстой"),
        Book(56789, false, "Гарри Поттер и философский камень", "Книга", 320, "Джоан Роулинг"),
        Book(24680, true, "1984", "Книга", 328, "Джордж Оруэлл"),
        Book(13579, true, "Маленький принц", "Книга", 96, "Антуан де Сент-Экзюпери"),

        // Газеты
        Newspaper(17245, false, "Сельская жизнь", "Газета", 794),
        Newspaper(67890, true, "Известия", "Газета", 250),
        Newspaper(98765, true, "Комсомольская правда", "Газета", 150),
        Newspaper(43210, false, "Аргументы и факты", "Газета", 52),
        Newspaper(55555, true, "Вечерняя Москва", "Газета", 100),

        // Диски
        Disc(54321, true, "Дэдпул и Росомаха", "Диск", "DVD"),
        Disc(11223, false, "Thriller", "Диск", "CD"),
        Disc(33445, true, "Bohemian Rhapsody", "Диск", "DVD"),
        Disc(77889, false, "Back in Black", "Диск", "CD"),
        Disc(99001, true, "The Dark Side of the Moon", "Диск", "CD")
    )

    /**
        Выполняет работу главного меню
    */
    while (true) {
        println("""
        1. Показать книги
        2. Показать газеты
        3. Показать диски
        4. Выход
    """.trimIndent())
        print("Введите команду: ")

        when (readlnOrNull()?.toIntOrNull()) {
            1 -> showItems(libraryItems.filterIsInstance<Book>(), "книг")
            2 -> showItems(libraryItems.filterIsInstance<Newspaper>(), "газет")
            3 -> showItems(libraryItems.filterIsInstance<Disc>(), "дисков")
            4 -> return
            else -> println("Нет такой команды.")
        }
    }
}

/**
* Является родительским классом для всех объектов, хранящихся в библиотеке
*
* @param id Идентификационный номер объекта
 * @param isAvailable Доступность объекта
 * @param title Название объекта
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
     * Проверяет возможно ли взятие объекта домой и меняет состояние доступности объекта
     */
    open fun takeHome() {
        println("Объект типа $type запрещено брать домой.")
    }
    /**
     * Проверяет возможно ли взятие объекта в зал и меняет состояние доступности объекта
     */
    open fun takeToRead() {
        println("Объект типа $type запрещено брать в зал.")
    }
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
) : LibraryItem(id, isAvailable, title, type) {
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

/**
 * Предоставляет тип "Газета" для библиотеки и реализует для этого типа функции родителя
 * @param issueNumber Номер выпуска газеты
 */
class Newspaper(
    id: Int,
    isAvailable: Boolean,
    title: String,
    type: String,
    private val issueNumber: Int
) : LibraryItem(id, isAvailable, title, type) {
    override fun getDetailInfo() : String {
        return "выпуск: $issueNumber газеты \"$title\" с id: $id доступен: ${if (isAvailable) "Да" else "Нет"}."
    }

    override fun takeToRead() {
        if (isAvailable) {
            println("Газета: \"$title\" с id: $id взята в зал.")
            isAvailable = !isAvailable
        } else
            println("Невозможно взять на чтение газету: \"$title\" с id: $id. Причина: газета недоступна.")
    }
}

/**
 * Предоставляет тип "Диск" для библиотеки и реализует для этого типа функции родителя
 * @param type Тип диска
 */
class Disc(
    id: Int,
    isAvailable: Boolean,
    title: String,
    type: String,
    private val typeOfDisc: String
) : LibraryItem(id, isAvailable, title, type) {
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

/**
 * Показывает объекты выбранного типа и реализует переход к взаимодействию с конкретным объектом
 *
 * @param items Список объектов выбранного типа
 * @param itemType Тип объекта
 */
fun showItems (
    items: List<LibraryItem>,
    itemType: String
) {
    if (items.isEmpty()){
        println("Нет доступных $itemType.")
        return
    }

    while (true) {
        println("Список доступных $itemType:")
        for ( ( index, item ) in items.withIndex() ) {
            println("${index+1}: ${item.getShortInfo()}")
        }

        val itemNumber : Int = selectingNumberOfObjectSafely(items.size)

        /**
         * Выход в главное меню
         */
        if (itemNumber == -1)
            return

        /**
         * Учет ошибки выбора объекта по номеру
         */
        if (itemNumber == 0) {
            println("Попробуйте еще раз.")
            continue
        }

        val selectedItem = items[itemNumber - 1]
        showItemMenu(selectedItem)
    }
}

/**
 * Безопасно выбирает объект по введеному пользователем номеру
 *
 * @param numbersBorder Номер последнего объекта
 * @return -1 или 0 или номер выбранного пользователем объекта
 */
fun selectingNumberOfObjectSafely(numbersBorder: Int): Int {
    runCatching {
        selectingNumberOfObject(numbersBorder)
    }.onSuccess { itemNumber ->
        return itemNumber
    }.onFailure {
        println("Ошибка при выборе номера: ${it.message}")
    }
    return 0
}

/**
 * Реализует функцию выбора пользователем объекта по номеру
 *
 * @param numbersBorder Номер последнего объекта
 * @return Номер выбранного объекта
 * @throws IllegalArgumentException Если введенный пользователем номер не существует
 */
private fun selectingNumberOfObject(numbersBorder : Int) : Int {
    println("Введите \"-1\" для выхода в главное меню.")
    print("Введите номер объекта: ")

    val itemNumber: Int = readlnOrNull()?.toIntOrNull() ?: 0

    if (itemNumber != -1)
        require((itemNumber) in 1..numbersBorder) { "Номер должен быть в промежутке от 1 до $numbersBorder" }

    return itemNumber
}

/**
 * Показывает меню для работы с объектом и обращается к функциям объекта
 *
 * @param item Выбранный пользователем объект
 */
fun showItemMenu(item: LibraryItem) {
    while (true) {
        println("""
        Выберите действие:
        1. Взять домой
        2. Читать в читальном зале
        3. Показать подробную информацию
        4. Вернуть
        5. Назад
      """.trimIndent())
        print("Введите действие: ")

        when (readlnOrNull()?.toIntOrNull()) {
            1 -> item.takeHome()
            2 -> item.takeToRead()
            3 -> println(item.getDetailInfo())
            4 -> item.bringBack()
            5 -> return
            else -> println("Нет такого действия.")
        }
    }
}
