package kr.asdfiop2021.mysolelife.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kr.asdfiop2021.mysolelife.MainActivity
import kr.asdfiop2021.mysolelife.R
import kr.asdfiop2021.mysolelife.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit  var auth : FirebaseAuth

    private lateinit  var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.btnLogin.setOnClickListener {

            val email = binding.txtEmail.text.toString()
            val password = binding.txtPass.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            startActivity(intent)

                            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()

                            // Sign in success, update UI with the signed-in user's information
                            /*Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            updateUI(user)*/
                        } else {

                            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                           // If sign in fails, display a message to the user.
                            /*Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            updateUI(null)*/
                        }
                    }

        }
    }
}