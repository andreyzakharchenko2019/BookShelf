package com.andreyzakharchenko.bookshelf

import android.app.Application
import com.andreyzakharchenko.bookshelf.data.AppContainer
import com.andreyzakharchenko.bookshelf.data.DefaultAppContainer

class BookApplication : Application() {
    val container by lazy {
        DefaultAppContainer()
    }
}