package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        save.setOnClickListener {
            var name = PersonName.text.toString();
            var age = PersonAge.text.toString();
            var id = PersonId.text.toString();

            // Create a new user with a first and last name
            val user = hashMapOf(
                "name" to name,
                "age" to age,
                "id" to id
            )

// Add a new document with a generated ID
            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(applicationContext, "${documentReference.id}", Toast.LENGTH_LONG).show();
                }
                .addOnFailureListener { e ->
                    Toast.makeText(applicationContext, "$e", Toast.LENGTH_LONG).show();
                }
        }
    }
}