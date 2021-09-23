package com.example.roomdatabasewithpaging3.Adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.roomdatabasewithpaging3.Data.Dogs
import com.modern.dogsuggester.R
import com.modern.dogsuggester.databinding.RecyclerViewRowBinding
import javax.inject.Inject

class DogsAdapter
@Inject
constructor() : PagingDataAdapter<Dogs,DogsAdapter.DogsViewHolder>(DiffUtils) {

    class DogsViewHolder(private val binding: RecyclerViewRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(dogs: Dogs){
            binding.apply {
                Glide.with(imageView)
                    .load(dogs.url)
                    .circleCrop()
                    .placeholder(R.drawable.dogplace)
                    .into(imageView)
            }
        }
    }

    object DiffUtils : DiffUtil.ItemCallback<Dogs>(){
        override fun areItemsTheSame(oldItem: Dogs, newItem: Dogs): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Dogs, newItem: Dogs): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(dogs = it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
       return DogsViewHolder(RecyclerViewRowBinding.inflate(LayoutInflater.from
           (parent.context),parent,false))
    }
}