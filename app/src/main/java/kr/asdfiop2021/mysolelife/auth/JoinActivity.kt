package kr.asdfiop2021.mysolelife.auth

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kr.asdfiop2021.mysolelife.MainActivity
import kr.asdfiop2021.mysolelife.R
import kr.asdfiop2021.mysolelife.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding : ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

        auth = Firebase.auth

        binding.btnJoin.setOnClickListener {

            var isGoToJoin = true

            var email = binding.textEmail.text.toString()
            var pass1 = binding.textPassword1.text.toString()
            var pass2 = binding.textPassword2.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if (pass1.isEmpty()) {
                Toast.makeText(this, "password를 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if (pass2.isEmpty()) {
                Toast.makeText(this, "password check를 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if (!pass1.equals(pass2)) {
                Toast.makeText(this, "password와 password check를 똑같이 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if(pass1.length < 6 ){
                Toast.makeText(this, "password는 6자리 이상 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if (isGoToJoin) {
                auth.createUserWithEmailAndPassword(email, pass1)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "createUserWithEmail:success")

                            Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()
                            var intent = Intent(this, MainActivity::class.java)
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()

                        }
                    }
            }
        }





    }
}