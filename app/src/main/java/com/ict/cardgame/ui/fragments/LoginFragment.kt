package com.ict.cardgame.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View

import androidx.fragment.app.FragmentTransaction
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ict.cardgame.R
import com.ict.cardgame.api.ServiceAPI
import com.ict.cardgame.databinding.FragmentLoginBinding
import com.ict.cardgame.model.Login
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            binding.btnLogin.setOnClickListener {
                val email = binding.etUsername.text.toString()// email: eve.holt@reqres.in
                val password = binding.etPassword.text.toString()
                val loginRequest = Login(email, password)

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://reqres.in/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val retrofitInstance = retrofit.create(ServiceAPI ::class.java)
                val loginCall =retrofitInstance.login(loginRequest)
                loginCall.enqueue(object : Callback<Login> {
                    override fun onResponse(call: Call<Login>, response: Response<Login>) {
                        if (response.isSuccessful||response.code()==200) {
                            val registrationResponse = response.body()
                            Toast.makeText(activity, "Useri  u be login", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_loginFragment_to_playersFragment)
//
                        } else {

                            Toast.makeText(activity, "Registration failed, please try again", Toast.LENGTH_SHORT).show()
                        }
                    }



                    override fun onFailure(call: Call<Login>, t: Throwable) {
                        Toast.makeText(activity, "Registration failed, please try again", Toast.LENGTH_SHORT).show()
                    }
                })





        }


}}