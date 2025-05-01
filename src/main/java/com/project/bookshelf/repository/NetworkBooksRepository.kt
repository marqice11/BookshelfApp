package com.project.bookshelf.repository

import com.project.bookshelf.model.BookItem
import com.project.bookshelf.network.BooksApiService

class NetworkBooksRepository (
    private val booksApiService: BooksApiService
) : BooksRepository {

    override suspend fun searchBooks(query: String): List <BookItem> {
        return booksApiService.searchBooks(query).items ?:emptyList()
    }
}

