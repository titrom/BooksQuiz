package com.example.mvvm.ui

import android.content.res.Resources
import androidx.lifecycle.*
import com.example.mvvm.R
import com.example.mvvm.adapter.entities.FinishBookItem
import com.example.mvvm.data.db.entities.Book
import com.example.mvvm.data.repo.BooksRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repo: BooksRepository): ViewModel() {

    private var numberOfQuestions = 10
    private var countCurrentAnswer = 0
    private var countQuestion = 0

    val currentBooks = arrayListOf<FinishBookItem>()

    val answerId : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val bookListQuestion: MutableLiveData<List<Book>> by lazy {
        MutableLiveData<List<Book>>()
    }

    val completeQuiz: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun addItem(items: List<Book>) = viewModelScope.launch {
        repo.insert(items)
    }

    private fun delete(item: Book) = viewModelScope.launch {
        repo.delete(item)
    }

    fun getBooksForQuestion() = repo.getBooksForQuestion()

    fun updateBooksForQuestion(){
        getBooksForQuestion().observeForever{
            if(it.isNotEmpty()){
                bookListQuestion.value = it
                answerId.value = (0..2).random()
            }
            else{
                it.forEach { item -> delete(item) }
            }
        }
    }

    fun nextQuestion(answerId: Int){

        if (countQuestion != numberOfQuestions){

            if(answerId == this.answerId.value) countCurrentAnswer++

            val correctFirstLine = bookListQuestion.value?.get(this.answerId.value!!)?.firstLine
            val imageIdAnswer = bookListQuestion.value?.get(answerId)?.imageId

            val finishBook = FinishBookItem(correctFirstLine!!, imageIdAnswer!!,answerId == this.answerId.value)

            currentBooks.add(finishBook)

            updateBooksForQuestion()
        }
        else {
            completeQuiz.value = "$countCurrentAnswer/$numberOfQuestions"
        }

        countQuestion++
    }

    fun restartQuiz(){
        updateBooksForQuestion()
        numberOfQuestions = 10
        countCurrentAnswer = 0
        countQuestion = 0
        currentBooks.clear()
    }
}