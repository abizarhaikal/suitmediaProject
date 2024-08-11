package com.example.suitmediaaplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediaaplication.data.DataItem
import com.example.suitmediaaplication.databinding.ItemUserBinding

class UserAdapter: PagingDataAdapter<DataItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback : OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback : OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = getItem(position)
        if (listItem != null) {
            holder.bind(listItem)
            holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(data =listItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    inner class MyViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: DataItem) {
            Glide.with(itemView.context)
                .load(listItem.avatar)
                .circleCrop()
                .into(binding.ivUser)

            binding.tvFirstName.text = listItem.firstName
            binding.tvLastName.text = listItem.lastName
            binding.tvEmail.text = listItem.email


        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataItem)
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                // Bandingkan seluruh properti untuk memastikan konten yang sama
                return oldItem == newItem
            }
        }
    }

}
