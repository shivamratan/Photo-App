package com.shivamratan.photoapp.db.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shivamratan.photoapp.db.dao.FavouritesDao
import com.shivamratan.photoapp.db.entity.FavouritesEntity

@Database(entities = [FavouritesEntity::class], version = 1)
abstract class PhotoDatabase: RoomDatabase() {
    abstract fun getFavouritesDao(): FavouritesDao
}