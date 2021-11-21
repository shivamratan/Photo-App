package com.shivamratan.photoapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shivamratan.photoapp.data.api.ApiService
import com.shivamratan.photoapp.data.response.PhotoItem
import com.shivamratan.photoapp.utils.ApiConst
import java.lang.Exception

class ImagePagingSource constructor(private val apiService: ApiService, val tag: String = "\"\""): PagingSource<Int, PhotoItem>() {
    override fun getRefreshKey(state: PagingState<Int, PhotoItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoItem> {
        val page = params.key?:1
        return try {
            val response = apiService.getPhotoList(pageNum = page, tags = tag)
            if(response.stat == ApiConst.STATUS_OK) {
                val photoList: List<PhotoItem> =
                    (response.photos?.photo ?: emptyList()).filterIsInstance<PhotoItem>()
                LoadResult.Page(
                    photoList,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (photoList.isEmpty()) null else (page + 1)
                )
            } else {
                LoadResult.Error(Exception(response.message?:"Something went wrong"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }


    }
}