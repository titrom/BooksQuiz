package com.example.mvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm.data.db.entities.Book

@Database(
    entities = [Book::class],
    version = 1
)
abstract class BooksDatabase : RoomDatabase() {

    abstract fun getBooksDao(): BooksDao

    companion object{
        @Volatile
        private var instance: BooksDatabase ?= null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context)
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            BooksDatabase::class.java, name = "BooksDB.dp"
        ).build()
    }
}