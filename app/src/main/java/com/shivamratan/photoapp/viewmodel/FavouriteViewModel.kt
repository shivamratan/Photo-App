package com.shivamratan.photoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivamratan.photoapp.data.repo.DatabaseRepository
import com.shivamratan.photoapp.db.entity.FavouritesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val databaseRepository: DatabaseRepository): ViewModel() {

    val favouriteLiveData = MutableLiveData<List<FavouritesEntity>>()
    val favouritePhotoList = ArrayList<FavouritesEntity>()

    fun deleteFavourite(favouritesEntity: FavouritesEntity) {
        viewModelScope.launch {
            databaseRepository.deleteFavourite(favouritesEntity)
        }
    }

    fun getAllFavourites() {
        viewModelScope.launch {
            val favouriteList = databaseRepository.getAllFavourite()
            favouriteLiveData.postValue(favouriteList)
        }
    }

}