package com.example.for_example

import adapters.LibraryAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.for_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: LibraryAdapter
    private var lastToast: Toast? = null

    private val items = mutableListOf(
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = LibraryAdapter().apply {
            setOnItemClickListener(this@MainActivity)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
            setHasFixedSize(true)
        }
//        adapter.submitList(items)
        updateAdapterList()
        
        setupSwipeToDelete()
    }

    private fun updateAdapterList() {
        adapter.submitList(items.toList())
    }

    override fun onItemClick(item: LibraryItem) {
        item.isAvailable = !item.isAvailable
        val position = items.indexOfFirst { it.id == item.id }
        if (position != -1) {
            items[position] = item
            adapter.notifyItemChanged(position, true)
            lastToast?.cancel()
            lastToast = Toast.makeText(this, "Элемент с id: ${item.id}", Toast.LENGTH_SHORT)
            lastToast?.show()
        }
    }

    private fun setupSwipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val newList = items.toMutableList().apply { removeAt(position) }
                    items.clear()
                    items.addAll(newList)
                    updateAdapterList()
                }
                updateAdapterList()
            }
        }).attachToRecyclerView(binding.recyclerView)
    }
}