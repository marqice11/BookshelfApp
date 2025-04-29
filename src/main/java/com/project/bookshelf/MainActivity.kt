package com.project.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.project.bookshelf.network.NetworkModule
import com.project.bookshelf.repository.NetworkBooksRepository
import com.project.bookshelf.ui.screens.BookShelfScreen
import com.project.bookshelf.viewmodel.BooksViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val repository = NetworkBooksRepository(NetworkModule.retrofitService)

            val viewModel = BooksViewModel(repository)

            BookShelfScreen(viewModel)
        }
    }
}
