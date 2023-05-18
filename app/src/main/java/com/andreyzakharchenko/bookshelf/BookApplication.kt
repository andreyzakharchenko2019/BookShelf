package com.andreyzakharchenko.bookshelf

import android.app.Application
import com.andreyzakharchenko.bookshelf.data.AppContainer
import com.andreyzakharchenko.bookshelf.data.DefaultAppContainer

class BookApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}