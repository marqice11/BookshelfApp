package com.project.bookshelf.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.bookshelf.model.BookItem
import com.project.bookshelf.repository.BooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BooksViewModel(
    private val booksRepository: BooksRepository
) : ViewModel() {

    private val _books = MutableStateFlow<List<BookItem>>(emptyList())

    val books: StateFlow<List<BookItem>> = _books

    fun searchBooks(query: String){
        viewModelScope.launch {
            val result = booksRepository.searchBooks(query)
            _books.value = result
        }
    }
}