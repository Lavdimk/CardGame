package com.ict.cardgame.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ict.cardgame.R
import com.ict.cardgame.databinding.FragmentPlayersBinding


class PlayersFragment : Fragment() {
    private lateinit var binding: FragmentPlayersBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentPlayersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val player1=binding.etPlayer1.text
        val player2=binding.etPlayer2.text
        binding.btnStart.setOnClickListener {
            if((player1.isEmpty())||(player2.isEmpty())){
                Toast.makeText(requireContext(),"Shkruani te dy emrat e lojetareve",Toast.LENGTH_SHORT).show()
        }
            else{
                findNavController().navigate(R.id.action_playersFragment_to_playFragment,Bundle().apply {
                    putString("playerone","${binding.etPlayer1.text.toString()}")
                    putString("playerTwo","${binding.etPlayer2.text.toString()}")

            })
            }
        }
    }
}



