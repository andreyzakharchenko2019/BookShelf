package com.andreyzakharchenko.bookshelf.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.andreyzakharchenko.bookshelf.ui.BookUiState

@Composable
fun HomeScreen(
    bookUiState: BookUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (bookUiState) {
        is BookUiState.Loading -> LoadingScreen(modifier)
        is BookUiState.Success -> BooksGridScreen(
            books = bookUiState.bookSearch,
            modifier = modifier
        )
        is BookUiState.Error -> ErrorScreen(retryAction = retryAction, modifier = modifier)
    }
}