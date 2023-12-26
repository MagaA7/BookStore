package com.example.bookstore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.databinding.FragmentHomeBinding
import com.example.bookstore.databinding.ItemHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val URL = "https://api.itbook.store/1.0/"
class homeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding
    lateinit var recyclerView: RecyclerView
    lateinit var bindingItem : ItemHomeBinding
    private val list: ArrayList<NewBooksApi> = ArrayList()
    private val array : ArrayList<String> = ArrayList()
    private val adapter = NewBookAdapter(emptyList())
    lateinit var recyclerView2 : RecyclerView
    lateinit var recyclerView3 : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        recyclerView = view.findViewById(R.id.recyclerView_newBooks)
        recyclerView2 = view.findViewById(R.id.recyclerView2)
        recyclerView3 = view.findViewById(R.id.recyclerView_androidBooks)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerView2.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerView3.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerView.setHasFixedSize(true)
        recyclerView2.setHasFixedSize(true)
        recyclerView3.setHasFixedSize(true)
        loadNewBooks()
        loadKotlinBooks()
        loadAndroidBooks()
        return view
    }

    fun loadNewBooks(){
        val retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build().create(API::class.java)
        val data = retrofit.newBooks()
        val kotlin = retrofit.kotlinBooks()
        data.enqueue(object : Callback<NewBooksApi> {
            override fun onResponse(call: Call<NewBooksApi>, response: Response<NewBooksApi>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val list = responseBody?.books
                    if (list != null) {
                        recyclerView.adapter = NewBookAdapter(list)
                    }
                }

            }

            override fun onFailure(call: Call<NewBooksApi>, t: Throwable) {
                Log.d("SKAZAK", "SDKAS")
            }
        })
                kotlin.enqueue(object : Callback<KotlinBooksApi> {
                override fun onResponse(call: Call<KotlinBooksApi>, response: Response<KotlinBooksApi>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        val list = responseBody?.books
                        if(list != null) {
                            recyclerView2.adapter = KotlinBookAdapter(list)
                        }
                    }

                }
                override fun onFailure(call: Call<KotlinBooksApi>, t: Throwable) {
                    Log.d("SKA","SDKAS")
                }
            })
        }

    fun loadKotlinBooks(){
        val retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build().create(API::class.java)
        val data = retrofit.kotlinBooks()
        data.enqueue(object : Callback<KotlinBooksApi> {
            override fun onResponse(call: Call<KotlinBooksApi>, response: Response<KotlinBooksApi>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val list = responseBody?.books
                    if(list != null) {
                        recyclerView2.adapter = KotlinBookAdapter(list)
                    }
                }

            }
            override fun onFailure(call: Call<KotlinBooksApi>, t: Throwable) {
                Log.d("SKA","SDKAS")
            }
        })
    }

    fun loadAndroidBooks(){
        val retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build().create(API::class.java)
        val data = retrofit.androidBooks()
        data.enqueue(object : Callback<AndroidBooksApi> {
            override fun onResponse(call: Call<AndroidBooksApi>, response: Response<AndroidBooksApi>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val list = responseBody?.books
                    if(list != null) {
                        recyclerView3.adapter = AndroidBookAdapter(list)
                    }
                }

            }
            override fun onFailure(call: Call<AndroidBooksApi>, t: Throwable) {
                Log.d("SKA","SDKAS")
            }
        })
    }
    }

