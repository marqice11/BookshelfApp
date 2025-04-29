package com.project.bookshelf.model

data class ImageLinks(
    val thumbnail : String?
)

data class VolumeInfo(
    val title : String,
    val authors: List<String>?,
    val imageLinks: ImageLinks?
)

data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class BookSearchResponse(
    val items: List<BookItem>?
)
