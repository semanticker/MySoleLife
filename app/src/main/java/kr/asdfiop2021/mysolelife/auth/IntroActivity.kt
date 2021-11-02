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
import kr.asdfiop2021.mysolelife.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)

        binding.btnLogin.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnJoin.setOnClickListener {
            var intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

        binding.btnNoAccount.setOnClickListener {
            auth.signInAnonymously()
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            startActivity(intent)

                            Toast.makeText(this, "익명 로그인 성공", Toast.LENGTH_SHORT).show();
                            // Sign in success, update UI with the signed-in user's information
                            /*Log.d(TAG, "signInAnonymously:success")
                            val user = auth.currentUser
                            updateUI(user)*/
                        } else {
                            Toast.makeText(this, "익명 로그인 실패", Toast.LENGTH_SHORT).show();
                            // If sign in fails, display a message to the user.
                            /*Log.w(TAG, "signInAnonymously:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            updateUI(null)*/
                        }
                    }
        }
    }
}