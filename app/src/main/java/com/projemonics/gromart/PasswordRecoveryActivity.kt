package com.projemonics.gromart


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.projemonics.gromart.databinding.ActivityPasswordRecoveryBinding
import com.google.firebase.auth.FirebaseAuth

class PasswordRecoveryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasswordRecoveryBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordRecoveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPasswordReset.setOnClickListener {
            val email = binding.email.text.toString().trim{ it <= ' '}
            if (email.isNotEmpty()) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener {task->
                        if(task.isSuccessful){
                            Toast.makeText(this, "Email sent to reset your password.", Toast.LENGTH_SHORT).show()
                            finish()
                        }else{
                            Toast.makeText(this@PasswordRecoveryActivity,task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                        }
                    }

            } else {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}