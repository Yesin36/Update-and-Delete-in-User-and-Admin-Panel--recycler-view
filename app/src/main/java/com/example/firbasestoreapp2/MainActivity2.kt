package com.example.firbasestoreapp2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firbasestoreapp2.databinding.ActivityMain2Binding
import com.example.firbasestoreapp2.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db = Firebase.firestore

        binding.nameup.setText(intent.getStringExtra("name"))
        binding.passup.setText(intent.getStringExtra("pass"))



        // Handle update button click
        binding.updatebtn.setOnClickListener {
            val documentId = intent.getStringExtra("id")

            if (documentId != null) { // Check if documentId is not null
                val user = hashMapOf(
                    "name" to binding.nameup.text.toString(),
                    "pass" to binding.passup.text.toString(),
                )

                db.collection("users").document(documentId).set(user)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Update successful", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Update failed: $e", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Document ID is missing", Toast.LENGTH_SHORT).show()
            }
        }


    }
}

