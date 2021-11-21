package com.shivamratan.photoapp.di.db

import android.content.Context
import androidx.room.Room
import com.shivamratan.photoapp.db.base.PhotoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDunzoDatabase(@ApplicationContext applicationContext: Context): PhotoDatabase {
        return Room.databaseBuilder(applicationContext, PhotoDatabase::class.java, "dunzo-db")
            .build()
    }
}