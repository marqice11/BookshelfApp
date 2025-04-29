package com.project.bookshelf.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.project.bookshelf.viewmodel.BooksViewModel
import androidx.compose.ui.graphics.Color


@Composable
fun BookShelfScreen(viewModel: BooksViewModel) {
    var searchQuery by remember {mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 32.dp,
                bottom = 16.dp
                )
    ) {
        OutlinedTextField(
            value =  searchQuery,
            onValueChange = {searchQuery= it },
            label = {Text("Search Book", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedContainerColor = Color.Black,
                unfocusedContainerColor = Color.Black,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.searchBooks(searchQuery.text)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults .buttonColors(
                containerColor = Color.White,
                contentColor =  Color.Black
            )
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        val books by viewModel.books.collectAsState()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier= Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(books) { book ->
                val thumbnailUrl = book.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://")

                println("Thumbnail URL: $thumbnailUrl")

                AsyncImage(
                    model = thumbnailUrl ?: "https://via.placeholder.com/150",
                    contentDescription = book.volumeInfo.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.7f)
                )
            }
        }
    }
}