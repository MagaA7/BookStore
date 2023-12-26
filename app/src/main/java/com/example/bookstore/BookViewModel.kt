package com.example.bookstore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookViewModel : ViewModel() {
    val selectedBook = MutableLiveData<SelectedBook>()

    fun selectedBook(book : SelectedBook){
        selectedBook.value = book
    }
}