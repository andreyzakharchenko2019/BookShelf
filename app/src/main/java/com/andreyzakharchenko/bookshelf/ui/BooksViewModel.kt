package com.andreyzakharchenko.bookshelf.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.andreyzakharchenko.bookshelf.BookApplication
import com.andreyzakharchenko.bookshelf.data.Book
import com.andreyzakharchenko.bookshelf.data.BookRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BookUiState {
    data class Success(val bookSearch: List<Book>) : BookUiState
    object Error : BookUiState
    object Loading : BookUiState
}

class BooksViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {

    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set

    init {
        getBooks("book")
    }

    fun getBooks(query: String, maxResult: Int = 40) {
        viewModelScope.launch {
            bookUiState = BookUiState.Loading
            bookUiState = try {
                BookUiState.Success(bookRepository.getBooks(query, maxResult))
            } catch (e: IOException) {
                BookUiState.Error
            } catch (e: HttpException) {
                BookUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookApplication)
                val bookRepository = application.container.bookRepository
                BooksViewModel(bookRepository = bookRepository)
            }
        }
    }
}