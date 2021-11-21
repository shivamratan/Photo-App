package com.shivamratan.photoapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FavouritesEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long = 0L

    @ColumnInfo(name = "owner")
    var owner: String = ""

    @ColumnInfo(name = "server")
    var server: String = ""

    @ColumnInfo(name = "ispublic")
    var ispublic: Int = 0

    @ColumnInfo(name = "isfriend")
    var isfriend: Int = 0

    @ColumnInfo(name = "farm")
    var farm: Int = 0

    @ColumnInfo(name = "secret")
    var secret: String = ""

    @ColumnInfo(name = "title")
    var title: String = ""

    @ColumnInfo(name = "isfamily")
    var isfamily: Int = 0
}