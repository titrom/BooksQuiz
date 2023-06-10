package com.example.mvvm.ui.dialog

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatDialog
import com.example.mvvm.adapter.FinishDialogItemAdapter
import com.example.mvvm.databinding.FinishDialogBinding
import com.example.mvvm.ui.MainViewModel

class FinishDialog(context: Context, private val viewModel: MainViewModel): AppCompatDialog(context) {

    private lateinit var viewBinding: FinishDialogBinding

    private lateinit var adapter: FinishDialogItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = FinishDialogBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        adapter = FinishDialogItemAdapter(viewModel.currentBooks)
        viewBinding.finishList.adapter = adapter
    }
}