package com.example.bookstore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class searchFragment : Fragment() {

    private lateinit var recyclerViewSearch: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var binding : FragmentSearchBinding
    private var mList = ArrayList<NewBooks>()
    lateinit var path : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        val view = binding.root
        path = "Default"
        recyclerViewSearch = view.findViewById(R.id.recyclerViewSearch)
        recyclerViewSearch.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewSearch.setHasFixedSize(true)
        searchView = view.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    path = query
                    searchBooks()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })
        return view
    }
    /////
    fun searchBooks(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.itbook.store/1.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(API::class.java)

        val data = retrofit.search(path)
        data.enqueue(object : Callback<SearchBooksApi> {
            override fun onResponse(call: Call<SearchBooksApi>, response: Response<SearchBooksApi>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val list = responseBody?.books
                    if (list != null) {
                        recyclerViewSearch.adapter = SearchAdapter(list)
                    }
                }

            }

            override fun onFailure(call: Call<SearchBooksApi>, t: Throwable) {
                Log.d("SKAZAK", "SDKAS")
            }
        })
    }
}