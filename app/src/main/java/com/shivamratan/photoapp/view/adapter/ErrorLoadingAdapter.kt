package com.shivamratan.photoapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.core.view.isVisible
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shivamratan.photoapp.databinding.LayoutErrorStateBinding

class ErrorLoadingAdapter constructor(private val retry: ()->Unit): LoadStateAdapter<ErrorLoadingAdapter.ErrorLoadingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ErrorLoadingViewHolder {
        return ErrorLoadingViewHolder(LayoutErrorStateBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ErrorLoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class ErrorLoadingViewHolder constructor(private val binding: LayoutErrorStateBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnRetry.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBarErrorState.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}