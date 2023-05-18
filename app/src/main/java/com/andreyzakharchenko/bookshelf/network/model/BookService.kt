package com.andreyzakharchenko.bookshelf.network.model

import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("volumes")
    suspend fun bookSearch(
        @Query("q") searchQuery: String,
        @Query("maxResult") maxResult: Int
    ): BookShelf
}