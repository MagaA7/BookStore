package com.example.bookstore

import retrofit2.Call
import retrofit2.http.GET
interface API {
    @GET("new")
    fun newBooks(): Call<NewBooksApi>

}