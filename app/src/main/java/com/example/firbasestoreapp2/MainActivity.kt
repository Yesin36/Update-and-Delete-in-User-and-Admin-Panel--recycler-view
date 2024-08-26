package com.example.firbasestoreapp2

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firbasestoreapp2.databinding.ActivityMainBinding
//import com.google.firebase.Firebase
//import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding

    private val TAG = "MainActivity" // Defining a TAG for logging


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var list = arrayListOf<users>()
        var rvadapter = RvAdapter(this,list)

        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = rvadapter


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db = Firebase.firestore

        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                list.clear()
                for (document in result) {
//                    Log.d(TAG, "${document.id} => ${document.data}")
                    var user = document.toObject(users::class.java)
                    user.id=document.id
                    list.add(user)

                }
                rvadapter.notifyDataSetChanged()

            }
            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents.", exception)
            }

    }
}