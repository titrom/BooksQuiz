package com.example.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.adapter.entities.FinishBookItem
import com.example.mvvm.databinding.FinishDialogItemBinding

class FinishDialogItemAdapter(var items: List<FinishBookItem>):
    RecyclerView.Adapter<FinishDialogItemAdapter.FinishDialogItemHolder>() {

    inner class FinishDialogItemHolder(val viewBinding: FinishDialogItemBinding):RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinishDialogItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.finish_dialog_item,parent,false)
        return FinishDialogItemHolder(FinishDialogItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: FinishDialogItemHolder, position: Int) {
        val finishBook = items[position]

        with(holder.viewBinding){
            positionText.text = (position+1).toString()

            bookImage.setImageDrawable(ResourcesCompat.getDrawable(root.resources, finishBook.imageId,null))

            question.text = finishBook.question

            if(finishBook.isCorrect){
                statusText.text = "Правильно"
                statusText.setTextColor(root.resources.getColor(R.color.green, null))
            }else{
                statusText.text = "Не правильно"
                statusText.setTextColor(root.resources.getColor(R.color.red, null))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}