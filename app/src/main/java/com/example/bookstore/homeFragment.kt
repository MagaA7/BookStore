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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerView.setHasFixedSize(true)
        loadNewBooks()
        return view
    }

    fun loadNewBooks(){
        val retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build().create(API::class.java)
        val data = retrofit.newBooks()
        data.enqueue(object : Callback<NewBooksApi> {
            override fun onResponse(call: Call<NewBooksApi>, response: Response<NewBooksApi>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        for(data in responseBody.books){
                            array.add(data.title)
                        }
                    }
                    recyclerView.adapter = NewBookAdapter(array)

                    Log.d("ARsen","$array")
                }

            }
            override fun onFailure(call: Call<NewBooksApi>, t: Throwable) {
                    Log.d("SKAZAK","SDKAS")
            }
        })
    }
}
