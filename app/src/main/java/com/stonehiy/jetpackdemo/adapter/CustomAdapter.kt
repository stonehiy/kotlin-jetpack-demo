package com.stonehiy.jetpackdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.stonehiy.jetpackdemo.entity.Author
import androidx.databinding.ViewDataBinding
import com.stonehiy.jetpackdemo.R
import com.stonehiy.jetpackdemo.databinding.ItemLayoutBinding


class CustomAdapter : PagedListAdapter<Author, CustomViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = DataBindingUtil.inflate<ItemLayoutBinding>(LayoutInflater.from(parent.context), R.layout.item_layout,
                parent,
                false)
        return CustomViewHolder(binding, binding.root)
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.toBinding(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Author>() {
            override fun areItemsTheSame(oldConcert: Author,
                                         newConcert: Author): Boolean =
                    oldConcert.id == newConcert.id

            override fun areContentsTheSame(oldConcert: Author,
                                            newConcert: Author): Boolean =
                    oldConcert == newConcert
        }
    }

}


class CustomViewHolder(private val binding: ItemLayoutBinding?, itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun toBinding(author: Author?) {
        binding?.author = author
        binding?.executePendingBindings()
    }


}