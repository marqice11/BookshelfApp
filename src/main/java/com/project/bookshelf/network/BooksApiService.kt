package com.project.bookshelf.network

import com.project.bookshelf.model.BookSearchResponse
//import com.project.shonenshelf.model.BookDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService{
    @GET("volumes")
    suspend fun searchBooks(@Query("q")query: String,
                            @Query("key") apiKey: String = "AIzaSyC1Qy6vhOlqy21wrdF00omwNYenG6fy4tU"
                            ): BookSearchResponse

    //@GET("volumes/{volumeId}")
    //suspend fun getBookById(@Path("volumeId")id: String): BookDetailResponse

}