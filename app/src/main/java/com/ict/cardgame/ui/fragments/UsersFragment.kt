package com.ict.cardgame.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.ict.cardgame.R
import com.ict.cardgame.adapter.UsersAdapter
import com.ict.cardgame.api.ServiceAPI
import com.ict.cardgame.databinding.FragmentUsersBinding
import com.ict.cardgame.model.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UsersFragment : Fragment() {
    private lateinit var binding: FragmentUsersBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentUsersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeAPICalls()
        goToSavedFragment()



    }

    private fun goToSavedFragment() {
        binding.btnswitch.setOnClickListener {
            findNavController().navigate(R.id.action_usersFragment_to_savedPlayer)
        }
    }


    fun makeAPICalls() {
        val progressBar = binding.progressBar
        progressBar.visibility = View.VISIBLE


        val retrofitInstance = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://reqres.in/api/")
            .build()
            .create(ServiceAPI::class.java)
        val getAllUser=retrofitInstance.getAllUsers()
        getAllUser.enqueue(object : Callback<Data?> {
            override fun onResponse(call: Call<Data?>, response: Response<Data?>) {

                progressBar.visibility = View.GONE
                val userslist= response.body()?.data
                val adapter= userslist?.let { UsersAdapter(it) }
                binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
                binding.rvUsers.adapter=adapter





            }
            override fun onFailure(call: Call<Data?>, t: Throwable) {
                Log.d(TAG, "onFailure ")

            }
        })

    }





}