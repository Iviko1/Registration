package com.example.auth_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var passwordInput2: EditText
    private lateinit var submitButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        emailInput = findViewById(R.id.editTextEmail)
        passwordInput = findViewById(R.id.editTextPassword)
        passwordInput2 = findViewById(R.id.editTextPassword2)
        submitButton = findViewById(R.id.submitButton)


        submitButton.setOnClickListener {

            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val password2 = passwordInput2.text.toString()
            if(email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                Toast.makeText(this, "Fill Out All Credentials", Toast.LENGTH_SHORT).show()
            } else if (password != password2) {
                Toast.makeText(this, "Passwords Don't Match", Toast.LENGTH_SHORT).show()
            }else {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                        emailInput.setText("")
                        passwordInput.setText("")
                        passwordInput2.setText("")
                    }
                }
            }
        }

    }
}