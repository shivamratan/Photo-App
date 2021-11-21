package com.shivamratan.photoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shivamratan.photoapp.data.api.ApiService
import com.shivamratan.photoapp.data.paging.ImagePagingSource
import com.shivamratan.photoapp.data.repo.DatabaseRepository
import com.shivamratan.photoapp.data.response.PhotoItem
import com.shivamratan.photoapp.db.entity.FavouritesEntity
import com.shivamratan.photoapp.utils.ApiConst
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val apiService: ApiService, private val databaseRepository: DatabaseRepository): ViewModel() {

    fun getPhotoList(tag: String="\"\""): Flow<PagingData<PhotoItem>> = Pager(
        config = PagingConfig(pageSize = ApiConst.PAGE_LIMIT, enablePlaceholders = false)) {
        ImagePagingSource(apiService = apiService, tag)
    }.flow.cachedIn(viewModelScope)


    fun saveFavourites(photoItem: PhotoItem) {
        viewModelScope.launch {
            val photoEntity = FavouritesEntity().apply {
                photoItem.let {
                    id = it.id?.toLong()?:0L
                    owner = it.owner?:""
                    server = it.server?:""
                    ispublic = it.ispublic?:-1
                    isfriend = it.isfriend?:-1
                    farm = it.farm?:-1
                    secret = it.secret?:""
                    title = it.title?:""
                    isfamily = it.isfamily?:-1
                }

            }
            databaseRepository.addFavourite(photoEntity)
        }
    }

    fun deleteFavourites(photoItem: PhotoItem) {
        viewModelScope.launch {
            val photoEntity = FavouritesEntity().apply {
                photoItem.let {
                    id = it.id?.toLong()?:0L
                    owner = it.owner?:""
                    server = it.server?:""
                    ispublic = it.ispublic?:-1
                    isfriend = it.isfriend?:-1
                    farm = it.farm?:-1
                    secret = it.secret?:""
                    title = it.title?:""
                    isfamily = it.isfamily?:-1
                }

            }
            databaseRepository.deleteFavourite(photoEntity)
        }
    }






}