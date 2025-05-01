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

@OptIn(ExperimentalMaterial3Api::class)
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
        TopAppBar(
            title = {
                Text(
                    text = "Book Shelf",
                    color = Color.White // This is for the title text
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.DarkGray, // Background of the top app bar
                titleContentColor = Color.White, // Title text color
                actionIconContentColor = Color.Yellow, // Action icons (if any)
                navigationIconContentColor = Color.Cyan // Navigation icon (if any)
            )
        )
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
        val filteredBooks = books.filter { it.volumeInfo.imageLinks?.thumbnail != null }


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(), // make sure this isn't inside a scrollable parent
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(filteredBooks) { book ->
                val thumbnailUrl = book.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://")

                Box(
                    modifier = Modifier
                        .height(220.dp) // consistent height
                        .fillMaxWidth() // only fill within the cell
                ) {
                    AsyncImage(
                        model = thumbnailUrl ?: "https://via.placeholder.com/150",
                        contentDescription = book.volumeInfo.title,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}