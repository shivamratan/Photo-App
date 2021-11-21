package com.shivamratan.photoapp.db.dao

import androidx.room.*
import com.shivamratan.photoapp.db.entity.FavouritesEntity

@Dao
interface FavouritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favouritesEntity: FavouritesEntity)

    @Query("SELECT * FROM FavouritesEntity")
    suspend fun getAllFavourite(): List<FavouritesEntity>

    @Update
    suspend fun updateFavourite(favouritesEntity: FavouritesEntity)

    @Delete
    suspend fun deleteFavourite(favouritesEntity: FavouritesEntity)

    @Query("DELETE FROM FavouritesEntity")
    suspend fun clearDatabase()
}