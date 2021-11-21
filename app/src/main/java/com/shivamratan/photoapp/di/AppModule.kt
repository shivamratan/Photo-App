package com.shivamratan.photoapp.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.shivamratan.photoapp.PhotoApp
import com.shivamratan.photoapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)
    }

    @Singleton
    @Provides
    fun provideGlideInstance(context: Context,
                             requestOptions: RequestOptions): RequestManager {
        return Glide.with(context).setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return PhotoApp.getApplicationContext()
    }
}