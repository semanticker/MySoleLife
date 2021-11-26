package kr.asdfiop2021.mysolelife.utils

import com.google.firebase.auth.FirebaseAuth

class FBAuth {

    //lateinit var

    companion object {

        private lateinit var auth: FirebaseAuth

        fun getUid() : String {
            auth = FirebaseAuth.getInstance()

            return auth.currentUser?.uid.toString()
        }
    }
}