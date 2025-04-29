package com.project.bookshelf.repository

import com.project.bookshelf.model.BookItem

interface BooksRepository {
    suspend fun searchBooks(query: String): List<BookItem>
}