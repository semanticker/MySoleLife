package kr.asdfiop2021.mysolelife.utils

import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class FBAuth {

    //lateinit var

    companion object {

        private lateinit var auth: FirebaseAuth

        fun getUid() : String {
            auth = FirebaseAuth.getInstance()

            return auth.currentUser?.uid.toString()
        }

        fun getTime() : String {
            val currentDateTime = Calendar.getInstance().time
            return SimpleDateFormat("yyyy-MM-dd HH:MM:ss", Locale.KOREA).format(currentDateTime)
        }
    }
}