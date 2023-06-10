package com.example.mvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.example.mvvm.R
import com.example.mvvm.adapter.BookItemAdapter
import com.example.mvvm.data.db.BooksDatabase
import com.example.mvvm.data.db.entities.Book
import com.example.mvvm.data.repo.BooksRepository
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.ui.dialog.FinishDialog
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: BookItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val database = BooksDatabase(this)
        val  repository = BooksRepository(database)
        val mainFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory = mainFactory)[MainViewModel::class.java]

        initAdapter()

        viewModel.getBooksForQuestion().observe(this){
            if(it.isEmpty())
                createData()
        }

        viewBinding.startButton.setOnClickListener {
            viewBinding.mainMotionLayout.transitionToEnd()
            viewModel.restartQuiz()
        }

        viewModel.updateBooksForQuestion()

        viewModel.bookListQuestion.observe(this) { books ->
            adapter.items = books
            adapter.notifyDataSetChanged()
        }

        viewModel.answerId.observe(this){
            viewBinding.firstLine.text = adapter.items[it].firstLine
        }

        viewModel.completeQuiz.observe(this){
            viewBinding.mainMotionLayout.transitionToStart()
            viewBinding.startTitle.text = it

            var dialog = FinishDialog(this, viewModel)
            dialog.show()
        }
    }

    private fun getTextBook(id: Int): String = resources.getString(id)

    private fun createData(){
        val data = listOf(
            Book(getTextBook(R.string.text_book_the_pilgrim_progress), R.drawable.book_the_pilgrim_progress),
            Book(getTextBook(R.string.text_book_robinson_crusoe), R.drawable.book_robinson_crusoe),
            Book(getTextBook(R.string.text_book_gulliver_travels), R.drawable.book_gulliver_travels),
            Book(getTextBook(R.string.text_book_nightmare_abbey), R.drawable.book_nightmare_abbey),
            Book(getTextBook(R.string.text_book_the_gold_bug), R.drawable.book_the_gold_bug),
            Book(getTextBook(R.string.text_book_the_life_and_opinions_of_tristram_shandy_gentleman), R.drawable.book_the_life_and_opinions_of_tristram_shandy_gentleman),
            Book(getTextBook(R.string.text_book_emma), R.drawable.book_emma),
            Book(getTextBook(R.string.text_book_frankenstein), R.drawable.book_frankenstein),
            Book(getTextBook(R.string.text_book_the_history_of_tom_jones_a_foundling), R.drawable.book_the_history_of_tom_jones_a_foundling),
            Book(getTextBook(R.string.book_clarissa), R.drawable.book_clarissa)
        )
        viewModel.addItem(data)
    }

    private fun initAdapter(){
        adapter = BookItemAdapter(listOf(), viewModel)
        viewBinding.listBooks.adapter = adapter
    }
}