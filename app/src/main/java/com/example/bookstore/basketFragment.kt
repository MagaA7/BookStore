package com.example.bookstore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.databinding.FragmentBasketBinding

class basketFragment : Fragment() {
   lateinit var binding : FragmentBasketBinding
   lateinit var recyclerView : RecyclerView
   lateinit var data : BookViewModel
    private val list = mutableListOf<SelectedBook>()
    private val selectedBookAdapter = SelectedBookAdapter(list)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasketBinding.inflate(inflater,container,false)
        val view = binding.root
        recyclerView = view.findViewById(R.id.recyclerView_basket)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = ViewModelProvider(requireActivity()).get(BookViewModel::class.java)
        data.selectedBooks.observe(viewLifecycleOwner, Observer { selectedBooks ->
            selectedBookAdapter.submitList(selectedBooks)
        })

    }
}