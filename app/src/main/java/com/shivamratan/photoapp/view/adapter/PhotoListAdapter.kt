package com.shivamratan.photoapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.shivamratan.photoapp.data.response.PhotoItem
import com.shivamratan.photoapp.databinding.LayoutPhotoItemBinding
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class PhotoListAdapter @Inject constructor(private val requestManager: RequestManager)
    : PagingDataAdapter<PhotoItem, PhotoListAdapter.PhotoViewHolder>(Diff) {

    var onItemClick: (_: String)->Unit = {}
    var onFavouritePhotoItem: (item: PhotoItem, imageView: AppCompatImageButton)-> Unit = { photoItem, imageView ->
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutPhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photoItem = getItem(position)
        photoItem?.also {
            holder.bind(it)
        }
    }

    inner class PhotoViewHolder(val binding: LayoutPhotoItemBinding) :RecyclerView.ViewHolder(binding.root) {

        init {

            itemView.setOnClickListener {
                val item = getItem(absoluteAdapterPosition)
                item?.let {
                    onItemClick.invoke(getFullScreenUrl(it))
                }
            }

            binding.btnLikePhoto.setOnClickListener {
                val photoItem = getItem(absoluteAdapterPosition)
                photoItem?.let {
                    onFavouritePhotoItem.invoke(it, binding.btnLikePhoto)
                }

            }
        }

        fun bind(photoItem: PhotoItem) {
            photoItem.let {
                binding.tvPhoto.text = it.title
                requestManager.load(getUrl(it)).into(binding.ivPhoto)
            }
        }

        private fun getUrl(photoItem: PhotoItem): String {
            return "https://farm${photoItem.farm?:""}.staticflickr.com/" +
                    "${photoItem.server?:""}/${photoItem.id?:""}_${photoItem.secret?:""}_m.jpg"
        }

        private fun getFullScreenUrl(photoItem: PhotoItem): String {
            return "https://farm${photoItem.farm?:""}.staticflickr.com/" +
                    "${photoItem.server?:""}/${photoItem.id?:""}_${photoItem.secret?:""}_b.jpg"
        }
    }

    object Diff: DiffUtil.ItemCallback<PhotoItem>() {
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem == newItem
        }

    }
}