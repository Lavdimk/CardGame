package com.ict.cardgame.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ict.cardgame.R
import com.ict.cardgame.databinding.FragmentSavedPlayerBinding
import com.squareup.picasso.Picasso


class SavedPlayer : Fragment() {
    private lateinit var binding: FragmentSavedPlayerBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding=FragmentSavedPlayerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        sharedPreferences()

    }

    private fun sharedPreferences() {
        val sharedPref = requireContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE)

        val avatar = sharedPref.getString("avatar", "")
        val lastName = sharedPref.getString("lastName", "")
        val firstName = sharedPref.getString("firstName", "")
        binding.tvLastName.text = lastName
        binding.tvFirstName.text = firstName
        Picasso.get().load(avatar).into(binding.imageView)
        val sharedPreferences = requireActivity().getSharedPreferences("winner", Context.MODE_PRIVATE)
        val winner = sharedPreferences.getString("winner", "")
        binding.tvwinner.text=winner

    }


}