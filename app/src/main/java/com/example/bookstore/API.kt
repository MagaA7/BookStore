package com.example.bookstore

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("new")
    fun newBooks(): Call<NewBooksApi>

    @GET("search/kotlin")
    fun kotlinBooks() : Call<KotlinBooksApi>

    @GET("search/android")
    fun androidBooks() : Call<AndroidBooksApi>

    @GET("search/{userInput}")
    fun search(@Path("userInput") userInput: String): Call<SearchBooksApi>}