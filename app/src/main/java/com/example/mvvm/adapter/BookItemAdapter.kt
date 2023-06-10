package com.example.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.data.db.entities.Book
import com.example.mvvm.databinding.BookItemBinding
import com.example.mvvm.ui.MainViewModel

class BookItemAdapter(var items: List<Book>, private val viewModel: MainViewModel) : RecyclerView.Adapter<BookItemAdapter.BookViewHolder>() {

    inner class BookViewHolder(val viewBuilding: BookItemBinding):RecyclerView.ViewHolder(viewBuilding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookViewHolder(BookItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val bookItem = items[position]

        with(holder.viewBuilding){
            val resources = root.resources;
            bookImage.setImageDrawable(ResourcesCompat.getDrawable(resources, bookItem.imageId,null))
        }

        holder.viewBuilding.bookImage.setOnClickListener{
            viewModel.nextQuestion(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}