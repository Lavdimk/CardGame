package com.ict.cardgame.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.ict.cardgame.R
import com.ict.cardgame.databinding.FragmentPlayBinding
import kotlin.random.Random


class PlayFragment : Fragment() {
    private lateinit var binding: FragmentPlayBinding
    var player1 = 0
    var player2 = 0
    lateinit var random: Random
    val cardsArray = intArrayOf(
        R.drawable.card1,
        R.drawable.card2,
        R.drawable.card3,
        R.drawable.card4,
        R.drawable.card5,
        R.drawable.card6,
        R.drawable.card7,
        R.drawable.card8,
        R.drawable.card9,
        R.drawable.card10,
        R.drawable.card11,
        R.drawable.card12,
        R.drawable.card13,
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        random = Random

        binding.playerOne.text = requireArguments().getString("playerone").toString()
        binding.playerTwo.text = requireArguments().getString("playerTwo").toString()
        binding.playerOne.visibility = View.VISIBLE



        binding.btn1play.setOnClickListener {
            val card1 = random.nextInt(cardsArray.size)
            val card2 = random.nextInt(cardsArray.size)
            setCardImage(card1, binding.card1)
            setCardImage(card2, binding.card2)



            if ((player1 <= 14) && (player2 <= 14)) {
                if (card1 > card2) {
                    player1++
                    binding.value1.text = player1.toString()
                } else {
                    player2++
                    binding.value2.text = player2.toString()
                }
            } else if (player1 > player2){
                binding.root.setBackgroundColor(Color.GREEN)
                Toast.makeText(requireContext(), "Fituesi eshte:${binding.playerOne.text}", Toast.LENGTH_SHORT).show();
                saveWinner(binding.playerOne.text.toString())

            }else{
                binding.root.setBackgroundColor(Color.GREEN)
                Toast.makeText(requireContext(), "Fituesi eshte:${binding.playerTwo.text}", Toast.LENGTH_SHORT).show();
                saveWinner(binding.playerTwo.text.toString())

            }
        }
    }

    private fun saveWinner(winner: String) {
        val sharedPreferences = requireActivity().getSharedPreferences("winner", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("winner", winner)
        editor.commit()

    }

    private fun setCardImage(number: Int, image: ImageView) {
        image.setImageResource(cardsArray[number])
    }

}