package com.example.for_example

/**
 * Функция фильтрации списка объектов по типу
 */
inline fun<reified T : LibraryItem> List<LibraryItem>.filterByType() : List<T> {
    return this.filterIsInstance<T>()
}

fun main() {

    val libraryItems = mutableListOf(
        // Книги
        Book(90743, true, "Маугли", "Книга", 202, "Джозеф Киплинг"),
        Book(12345, true, "Война и мир", "Книга", 1225, "Лев Толстой"),
        Book(56789, false, "Гарри Поттер и философский камень", "Книга", 320, "Джоан Роулинг"),
        Book(24680, true, "1984", "Книга", 328, "Джордж Оруэлл"),
        Book(13579, true, "Маленький принц", "Книга", 96, "Антуан де Сент-Экзюпери"),

        // Газеты
        Newspaper(17245, false, "Сельская жизнь", "Газета", 794, "Май"),
        Newspaper(67890, true, "Известия", "Газета", 250, "Сентябрь"),
        Newspaper(98765, true, "Комсомольская правда", "Газета", 150, "Июнь"),
        Newspaper(43210, false, "Аргументы и факты", "Газета", 52, "Август"),
        Newspaper(55555, true, "Вечерняя Москва", "Газета", 100, "Февраль"),

        // Диски
        Disc(54321, true, "Дэдпул и Росомаха", "Диск", "DVD"),
        Disc(11223, false, "Thriller", "Диск", "CD"),
        Disc(33445, true, "Bohemian Rhapsody", "Диск", "DVD"),
        Disc(77889, false, "Back in Black", "Диск", "CD"),
        Disc(99001, true, "The Dark Side of the Moon", "Диск", "CD")
    )

    val manager = Manager()

    /**
        Выполняет работу главного меню
    */
    while (true) {
        println("""
        1. Показать книги
        2. Показать газеты
        3. Показать диски
        4. Меню менеджера
        5. Выход
    """.trimIndent())
        print("Введите команду: ")

        when (readlnOrNull()?.toIntOrNull()) {
            1 -> showItems(libraryItems.filterByType<Book>(), "книг", libraryItems)
            2 -> showItems(libraryItems.filterByType<Newspaper>(), "газет", libraryItems)
            3 -> showItems(libraryItems.filterByType<Disc>(), "дисков", libraryItems)
            4 -> managerMenu(manager)
            5 -> return
            else -> println("Нет такой команды.")
        }
    }
}

var idCounter: Int = 99005

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

/**
 * Реализует книжный магазин для менеджера
 */
class BookStore : Store<Book> {
    override fun sell(): Book = Book(99002, true, "SomeBook", "Книга", 1986, "SomeAuthor")
}

/**
 * Реализует магазин дисков для менеджера
 */
class DiscStore : Store<Disc> {
    override fun sell() : Disc = Disc(99003, true, "SomeDisc", "Диск", "CD")
}

/**
 * Реализует газетный ларек для менеджера
 */
class NewspaperKiosk : Store<Newspaper> {
    override fun sell() : Newspaper = Newspaper(99004, true, "SomeNewspaper", "Газета", 5148, "Март")
}

/**
 * Класс менеджера, реализует функцию покупки объектов
 */
class Manager {
    fun <T : LibraryItem> buy(store: Store<T>) : T {
        return store.sell()
    }
}

/**
 * Реализует оцифровку объектов в CD диск
 */
class CDDigitizer : Digitizer<Disc> {
    override fun digitize(item: LibraryItem) : Disc{
        return Disc (
            id = idCounter++,
            title = "Цифровая копия. ${item.type}. \"${item.title}\"",
            isAvailable = true,
            type = "Диск",
            typeOfDisc = "CD"
        )
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
    itemType: String,
    targetList: MutableList<LibraryItem>
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
        showItemMenu(selectedItem, targetList)
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
fun showItemMenu(item: LibraryItem, targetList: MutableList<LibraryItem>) {
    val cdDigitazer = CDDigitizer()
    while (true) {
        println("""
        Выберите действие:
        1. Взять домой
        2. Читать в читальном зале
        3. Показать подробную информацию
        4. Вернуть
        5. Оцифровать
        6. Назад
      """.trimIndent())
        print("Введите действие: ")

        when (readlnOrNull()?.toIntOrNull()) {
            1 -> {
                if (item is HomeTakeble)
                    item.takeHome()
                else
                    unavailableAction(item.type)
            }
            2 -> {
                if (item is LibraryReadble)
                    item.takeToRead()
                else
                    unavailableAction(item.type)
            }
            3 -> println(item.getDetailInfo())
            4 -> item.bringBack()
            5 -> {
                if (item.isAvailable && item is Digitizable) {
                    targetList.add(cdDigitazer.digitize(item))
                    println("Цифровая копия создана.")
                }
                else if (!item.isAvailable)
                    println("Невозможно взять на оцифровку объект типа ${item.type} \"${item.title}\" с id: ${item.id}. Причина: объект недоступен")
                else
                    unavailableAction(item.type)
            }
            6 -> return
            else -> println("Нет такого действия.")
        }
    }
}

/**
 * Выводит сообщение, когда действие недоступно для объекта
 *
 * @param type Строка типа объекта, для которого действие недоступно
 */
fun unavailableAction(type: String) {
    println("Объекту типа \"$type\" данное действие недоступно.")
}

fun managerMenu(manager: Manager) {
    while (true) {
        println("""
            Вы в меню менеджера.
            Введите номер действия:
            1. Купить книгу
            2. Купить газету
            3. Купить диск
            4. Назад
        """.trimIndent())
        print("Введите команду: ")

        when(readlnOrNull()?.toIntOrNull()) {
            1 -> {
                val book = manager.buy(BookStore())
                println("Менеджером куплена книга ${book.title}, ей присвоен id: ${book.id}")
            }
            2 -> {
                val disc = manager.buy(DiscStore())
                println("Менеджером куплен диск ${disc.title}, ему присвоен id: ${disc.id}")
            }
            3 -> {
                val newspaper = manager.buy(NewspaperKiosk())
                println("Менеджером куплена газета ${newspaper.title}, ей просвоен id: ${newspaper.id}")
            }
            4 -> return
            else -> println("Нет такой команды.")
        }
    }
}

/**
 * Интерфейс всех объектов, которые можно брать в зал
 */
interface HomeTakeble {
    /**
     * Проверяет возможно ли взятие объекта домой и меняет состояние доступности объекта
     */
    fun takeHome()
}

/**
 * Интерфейс всех объектов, которые можно брать в зал
 * */
interface LibraryReadble {
    /**
     * Проверяет возможно ли взятие объекта в зал и меняет состояние доступности объекта
     */
    fun takeToRead()
}

/**
 * Интерфейс для магазинов
 */
interface Store<out T : LibraryItem> {
    fun sell() : T
}

/**
 * Интерфейс для носителей, которых можно оцифровать
 */
interface Digitizable

/**
 * Интерфейс всех цифровых носителей
 */
interface DigitalMedium

/**
 * Интерфейс для разных оцифровщиков
 */
interface Digitizer<out Output : DigitalMedium> {
    fun digitize(item: LibraryItem) : Output
}