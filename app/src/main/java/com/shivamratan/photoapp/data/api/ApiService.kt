package com.shivamratan.photoapp.data.api

import com.shivamratan.photoapp.data.response.PhotoListResponse
import com.shivamratan.photoapp.utils.ApiConst
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("rest")
    suspend fun getPhotoList(@Query("page") pageNum: Int
                     , @Query("tags") tags: String = "\"\""
                     , @Query("per_page") pageLimit: Int = ApiConst.PAGE_LIMIT
                     , @Query("nojsoncallback") nojsoncallback: Int = 1
                     , @Query("method") method: String = ApiConst.METHOD_PHOTO
                     , @Query("api_key") apiKey: String = ApiConst.API_KEY
                     , @Query("format") format: String = ApiConst.FORMAT_JSON): PhotoListResponse
}