package com.shivamratan.photoapp.data.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PhotoListResponse(

	@field:SerializedName("stat")
	val stat: String? = null,

	@field:SerializedName("photos")
	val photos: Photos? = null,

	@field:SerializedName("message")
	val message: String? = null
)

@Keep
data class PhotoItem(

	@field:SerializedName("owner")
	val owner: String? = null,

	@field:SerializedName("server")
	val server: String? = null,

	@field:SerializedName("ispublic")
	val ispublic: Int? = null,

	@field:SerializedName("isfriend")
	val isfriend: Int? = null,

	@field:SerializedName("farm")
	val farm: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("secret")
	val secret: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("isfamily")
	val isfamily: Int? = null,

	var isFavourite: Boolean = false
)

@Keep
data class Photos(

	@field:SerializedName("perpage")
	val perpage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("pages")
	val pages: Int? = null,

	@field:SerializedName("photo")
	val photo: List<PhotoItem?>? = null,

	@field:SerializedName("page")
	val page: Int? = null
)
