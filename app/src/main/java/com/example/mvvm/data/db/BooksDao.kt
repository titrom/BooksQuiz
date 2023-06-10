package com.example.mvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvm.data.db.entities.Book


@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Book>)

    @Delete
    suspend fun delete(item: Book)

    @Query("SELECT * FROM book ORDER BY RANDOM() LIMIT 3")
    fun getBooksForQuestion(): LiveData<List<Book>>
}