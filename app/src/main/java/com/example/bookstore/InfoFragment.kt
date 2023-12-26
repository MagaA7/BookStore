package com.example.bookstore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.bookstore.databinding.FragmentOptionBinding
import com.squareup.picasso.Picasso

class InfoFragment : Fragment() {
    lateinit var binding : FragmentOptionBinding
    lateinit var viewModel : BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOptionBinding.inflate(inflater, container, false)
        val view = binding.root

        var basketTitle = ""
        var basketImage = ""
        var basketPrice = ""

        val kotlin_title = arguments?.getString("kotlinBooks_title")
        val kotlin_image = arguments?.getString("kotlinBooks_image")
        val kotlin_price = arguments?.getString("kotlinBooks_price")

        if(kotlin_title != null && kotlin_image != null && kotlin_price != null){
            Picasso.get().load(kotlin_image).into(binding.imageViewSelected)
            binding.title.text = kotlin_title
            binding.price.text = kotlin_price
            basketTitle = kotlin_title
        }

        val new_title = arguments?.getString("newBook_title")
        val new_image = arguments?.getString("newBook_image")
        val new_price = arguments?.getString("newBook_price")

        if(new_title != null && new_image != null && new_price != null) {
            Picasso.get().load(new_image).into(binding.imageViewSelected)
            binding.title.text = new_title
            binding.price.text = new_price
            basketTitle = new_title
            basketImage = new_image
            basketPrice = new_price
        }

        val android_title = arguments?.getString("androidBooks_title")
        val android_image = arguments?.getString("androidBooks_image")
        val android_price = arguments?.getString("androidBooks_price")

        if(android_title != null && android_image != null && android_price != null){
            Picasso.get().load(android_image).into(binding.imageViewSelected)
            binding.title.text = android_title
            binding.price.text = android_price
            basketTitle = android_title
            basketImage = android_image
            basketPrice = android_price
        }
            viewModel = ViewModelProvider(requireActivity()).get(BookViewModel::class.java)

            binding.buttonBasket.setOnClickListener {
                val current = SelectedBook(
                    title = basketTitle,
                    image = basketImage,
                    price = basketPrice,
                    url = "null"
                )

                viewModel.selectedBook(current)
            }

        binding.buttonBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_infoFragment_to_homeFragment)
        }
        return view
    }
}