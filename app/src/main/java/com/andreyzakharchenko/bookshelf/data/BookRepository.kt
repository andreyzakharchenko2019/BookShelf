package com.andreyzakharchenko.bookshelf.data

import com.andreyzakharchenko.bookshelf.network.model.BookService

interface BookRepository {
    suspend fun getBooks(query: String, maxResult: Int): List<Book>
}

class NetworkBooksRepository(
    private val bookService: BookService
) : BookRepository {
    override suspend fun getBooks(query: String, maxResult: Int
    ): List<Book> = bookService.bookSearch(query, maxResult).items.map { items ->
        Book(
            title = items.volumeInfo?.title,
            preview = items.volumeInfo?.previewLink,
            imageLink = items.volumeInfo?.imageLinks?.thumbnail
        )
    }
}