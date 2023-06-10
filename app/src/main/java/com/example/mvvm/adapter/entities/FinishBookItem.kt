package com.example.mvvm.adapter.entities

import com.example.mvvm.data.db.entities.Book

data class FinishBookItem(
    var question: String,
    var imageId: Int,
    var isCorrect: Boolean
)
