package com.shivamratan.photoapp.data.repo

import com.shivamratan.photoapp.data.api.ApiService
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class NetworkRepository @Inject constructor(private val apiService: ApiService) {


}