package com.projemonics.gromart

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.projemonics.gromart.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnTxtLogin.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.signUp.setOnClickListener {

            val nameF = binding.fname.text.toString()
            val nameL = binding.lname.text.toString()
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()
            val confirmPass = binding.confirmPassword.text.toString()

            if(nameF.isNotEmpty() && nameL.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if(pass == confirmPass){
                    if (pass.length >6) {
                        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    val intent = Intent(this, SignInActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(
                                        this,
                                        it.exception.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    }else{
                        binding.tvPass.requestFocus()
                        Toast.makeText(this, "Password cannot be less than seven characters", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    binding.tvCpass.requestFocus()
                    binding.tvPass.requestFocus()
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }

            }else{
                binding.tvFname.requestFocus()
                binding.tvLname.requestFocus()
                binding.tvPass.requestFocus()
                binding.tvCpass.requestFocus()
                binding.tvMail.requestFocus()
                Toast.makeText(this, "Please fill out all Fields", Toast.LENGTH_SHORT).show()
            }

        }
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(baseContext, "Landscape Mode", Toast.LENGTH_SHORT).show()
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(baseContext, "Portrait Mode", Toast.LENGTH_SHORT).show()
        }
    }
}