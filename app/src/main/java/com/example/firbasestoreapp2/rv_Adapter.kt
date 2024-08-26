package com.example.firbasestoreapp2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.firbasestoreapp2.databinding.RvItemBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RvAdapter(var context: Context, var list: ArrayList<users>) :
    RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    class ViewHolder(var binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = list[position]
        holder.binding.name.text = user.name  ?: "No Name"
        holder.binding.pass.text = user.pass  ?: "No Password"
        holder.binding.delete.setOnClickListener {
            val db = Firebase.firestore
            db.collection("users").document(list.get(position).id!!).delete()

                .addOnSuccessListener {
                    Toast.makeText(context, "Deleted ", Toast.LENGTH_SHORT).show()
                    notifyItemRemoved(position)
                } .addOnFailureListener {
                    Toast.makeText(context, "Failed to delete ", Toast.LENGTH_SHORT).show()
                }

        }
    }
}

