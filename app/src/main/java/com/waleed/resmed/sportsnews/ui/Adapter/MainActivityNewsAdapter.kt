package com.waleed.resmed.sportsnews.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.waleed.resmed.sportsnews.R
import com.waleed.resmed.sportsnews.databinding.ViewHolderNewsBinding
import com.waleed.resmed.sportsnews.models.NewsResult

class MainActivityNewsAdapter(val data : List<NewsResult>) : RecyclerView.Adapter<MainActivityNewsAdapter.ViewHolder>() {

    data class ViewHolder(val binding : ViewHolderNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ViewHolderNewsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.view_holder_news,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.run {
            newsItem = data.get(position)
        }
    }

    override fun getItemCount(): Int {
        return data.count()
    }

}