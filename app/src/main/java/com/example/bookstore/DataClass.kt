package com.example.bookstore

data class NewBooksApi(
    var books : List<NewBooks>
)

data class NewBooks(
    var image : String,
    var title : String,
    var price : String,
    var url : String
)
