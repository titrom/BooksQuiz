package com.example.mvvm.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class Book(
    @ColumnInfo(name = "book_first_line")
    var firstLine: String = "",

    @ColumnInfo(name = "book_id_image")
    var imageId: Int = 0
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}
