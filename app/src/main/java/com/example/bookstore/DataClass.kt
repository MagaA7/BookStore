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

data class KotlinBooksApi(
    var books : List<KotlinBooks>
)

data class KotlinBooks(
    var title : String,
    var price : String,
    var image: String,
    var url : String
)

data class AndroidBooksApi(
    var books : List<AndroidBooks>
)

data class AndroidBooks(
    var title : String,
    var price : String,
    var image: String,
    var url : String
)

