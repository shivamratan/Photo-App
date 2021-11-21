package com.shivamratan.photoapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.shivamratan.photoapp.R
import com.shivamratan.photoapp.databinding.LayoutPhotoItemBinding
import com.shivamratan.photoapp.db.entity.FavouritesEntity
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class FavouritePhotoAdapter @Inject constructor(private val requestManager: RequestManager): RecyclerView.Adapter<FavouritePhotoAdapter.FavouritePhotoViewHolder>() {

    private var itemList: List<FavouritesEntity> = ArrayList()
    var onItemClick: (_: String)->Unit = {}
    var onFavouritePhotoItem: (_: FavouritesEntity)-> Unit = {}

    fun updateFavouriteList(favouriteList: List<FavouritesEntity>) {
        itemList = favouriteList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritePhotoViewHolder {
        return FavouritePhotoViewHolder(LayoutPhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FavouritePhotoViewHolder, position: Int) {
        holder.bind(itemList.get(position))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    inner class FavouritePhotoViewHolder(val binding: LayoutPhotoItemBinding) :RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val item = itemList.get(absoluteAdapterPosition)
                item.let {
                    onItemClick.invoke(getFullScreenUrl(it))
                }
            }

            binding.btnLikePhoto.setOnClickListener {
                onFavouritePhotoItem.invoke(itemList.get(absoluteAdapterPosition))
            }
        }

        fun bind(photoItem: FavouritesEntity) {
            photoItem.let {
                binding.btnLikePhoto.setImageResource(R.drawable.ic_favorite_photo)
                binding.tvPhoto.text = it.title
                requestManager.load(getUrl(it)).into(binding.ivPhoto)
            }
        }

        private fun getUrl(photoItem: FavouritesEntity): String {
            return "https://farm${photoItem.farm?:""}.staticflickr.com/" +
                    "${photoItem.server?:""}/${photoItem.id?:""}_${photoItem.secret?:""}_m.jpg"
        }

        private fun getFullScreenUrl(photoItem: FavouritesEntity): String {
            return "https://farm${photoItem.farm?:""}.staticflickr.com/" +
                    "${photoItem.server?:""}/${photoItem.id?:""}_${photoItem.secret?:""}_b.jpg"
        }
    }
}