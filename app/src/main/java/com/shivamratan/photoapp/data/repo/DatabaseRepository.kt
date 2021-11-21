package com.shivamratan.photoapp.data.repo

import com.shivamratan.photoapp.db.base.PhotoDatabase
import com.shivamratan.photoapp.db.dao.FavouritesDao
import com.shivamratan.photoapp.db.entity.FavouritesEntity
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DatabaseRepository @Inject constructor(private val dataBase: PhotoDatabase) {

    private val favouritesDao: FavouritesDao by lazy { dataBase.getFavouritesDao() }

    suspend fun addFavourite(favouritesEntity: FavouritesEntity) = favouritesDao.insertFavourite(favouritesEntity)

    suspend fun getAllFavourite() = favouritesDao.getAllFavourite()

    suspend fun deleteFavourite(favouritesEntity: FavouritesEntity) = favouritesDao.deleteFavourite(favouritesEntity)
}