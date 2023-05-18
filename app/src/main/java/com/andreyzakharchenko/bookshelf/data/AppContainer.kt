package com.andreyzakharchenko.bookshelf.data

import com.andreyzakharchenko.bookshelf.network.model.BookService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val bookRepository: BookRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://www.googleapis.com/books/v1/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: BookService by lazy {
        retrofit.create(BookService::class.java)
    }

    override val bookRepository: BookRepository by lazy {
        NetworkBooksRepository(retrofitService)
    }
}