package com.project.bookshelf.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity (
    @PrimaryKey val id: String,
    val title: String,
    val authors : String?,
    val thumbnailUrl: String?
)