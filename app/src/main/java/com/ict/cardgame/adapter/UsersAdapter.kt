package com.ict.cardgame.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.ict.cardgame.R
import com.ict.cardgame.api.ServiceAPI
import com.ict.cardgame.model.User
import com.squareup.picasso.Picasso


class UsersAdapter (private var listOfUsers:List<User>): RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

        class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val tvId: TextView =itemView.findViewById(R.id.tvId)
            val tvFirstName: TextView =itemView.findViewById(R.id.tvFirstName)
            val tvLastname: TextView =itemView.findViewById(R.id.tvLastName)
            val image: ImageView =itemView.findViewById(R.id.imageView)
            val btn: AppCompatButton =itemView.findViewById(R.id.btnchallenge)
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
            val view= LayoutInflater.from(parent.context).inflate(R.layout.users_details,parent,false)
            return UserViewHolder(view)
        }

        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            val user = listOfUsers[position]
            holder.tvId.text = user.id.toString()
            holder.tvFirstName.text = user.first_name
            holder.tvLastname.text = user.last_name
            holder.btn.setOnClickListener {

                val avatar = user.avatar
                val lastName = user.last_name
                val firstName = user.first_name
                val sharedPref = holder.itemView.context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("avatar", avatar)
                editor.putString("lastName", lastName)
                editor.putString("firstName", firstName)

                editor.apply()

            }

            Picasso.get().load(user.avatar).into(holder.image)




                    }

    override fun getItemCount(): Int {
        return listOfUsers.size
    }
                }






