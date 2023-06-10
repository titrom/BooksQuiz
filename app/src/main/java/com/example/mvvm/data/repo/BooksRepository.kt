package com.example.mvvm.data.repo

import com.example.mvvm.data.db.BooksDatabase
import com.example.mvvm.data.db.entities.Book

class BooksRepository(private val db: BooksDatabase) {

    suspend fun insert(items: List<Book>) = db.getBooksDao().insert(items)

    suspend fun delete(item: Book) = db.getBooksDao().delete(item)

    fun getBooksForQuestion() = db.getBooksDao().getBooksForQuestion()
}