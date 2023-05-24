package com.andreyzakharchenko.bookshelf.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andreyzakharchenko.bookshelf.R
import com.andreyzakharchenko.bookshelf.data.Book

@Composable
fun BooksGridScreen(
    books: List<Book>,
    modifier: Modifier
) {
    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp),
    contentPadding = PaddingValues(4.dp)
    ) {
        itemsIndexed(books) { _, book -> 
            BooksCard(book = book, modifier)
        }
    }
}

@Composable
fun BooksCard(
    book: Book,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .requiredHeight(296.dp),
        elevation = 8.dp
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            book.title?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                )
            }
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(book.imageLink?.replace("http:", "https:")).crossfade(true).build(),
                contentDescription = stringResource(id = R.string.content_description),
                error = painterResource(id = R.drawable.ic_book_96),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentScale = ContentScale.Crop
            )
        }
    }
}