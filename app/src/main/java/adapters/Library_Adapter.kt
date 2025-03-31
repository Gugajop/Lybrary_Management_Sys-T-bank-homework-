package adapters

import Extentions.dp
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.for_example.LibraryItem
import com.example.for_example.OnItemClickListener
import com.example.for_example.databinding.LibraryItemBinding

class Library_Adapter : RecyclerView.Adapter<Library_Adapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<LibraryItem>() {
        override fun areItemsTheSame(oldItem: LibraryItem, newItem: LibraryItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: LibraryItem, newItem: LibraryItem) =
            oldItem == newItem

        override fun getChangePayload(oldItem: LibraryItem, newItem: LibraryItem): Any? {
            return if (oldItem.isAvailable != newItem.isAvailable) true else null
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    inner class ViewHolder(private val binding: LibraryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LibraryItem, position: Int) {
            binding.apply {
                this.item = item
                this.position = position
                this.clickListener = itemClickListener
                cardView.elevation = if (item.isAvailable) 10f.dp else 1f.dp
                title.alpha = if (item.isAvailable) 1f else 0.3f
                executePendingBindings()
            }
        }
    }

    private var itemClickListener : OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LibraryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(differ.currentList[position], position)

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: List<Any>) {
        if (payloads.isNotEmpty()) {
            holder.bind(differ.currentList[position], position)
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun getItemCount() = differ.currentList.size

    fun submitList(newList: List<LibraryItem>) {
        differ.submitList(newList)
    }
}