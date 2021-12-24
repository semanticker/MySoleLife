package kr.asdfiop2021.mysolelife.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kr.asdfiop2021.mysolelife.R
import kr.asdfiop2021.mysolelife.databinding.ActivityBoardEditBinding
import kr.asdfiop2021.mysolelife.databinding.ActivityBoardInsideBinding
import kr.asdfiop2021.mysolelife.utils.FBAuth
import kr.asdfiop2021.mysolelife.utils.FBRef

class BoardEditActivity : AppCompatActivity() {

    private lateinit var key:String

    private lateinit var binding : ActivityBoardEditBinding

    private val TAG = BoardEditActivity::class.java.simpleName

    private lateinit var writerUid : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_edit)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_edit)

        key = intent.getStringExtra("key").toString()
        getBoardData(key)

        getImageData(key)

        binding.btnEdit.setOnClickListener {
            editBoardData(key)
        }

    }

    private fun editBoardData(key: String) {

        val title = binding.textTitle.text.toString();
        val content = binding.textContent.text.toString();
        val time = FBAuth.getTime()

        FBRef.boardRef
            //.push()
            .child(key)
            .setValue(BoardModel(title, content, writerUid, time))

        Toast.makeText(this, "수정완료", Toast.LENGTH_LONG).show()
        finish()

    }

    private fun getImageData(key: String) {

        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child(key + ".png")

        // ImageView in your Activity
        val imageView = findViewById<ImageView>(R.id.imageArea)

        val imageViewFromFB = binding.imgPlus


        // Download directly from StorageReference using Glide
        // (See MyAppGlideModule for Loader registration)
        /*Glide.with(this *//* context *//*)
            .load(storageReference)
            .into(imageView)*/

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {
                Glide.with(this /* context */)
                    .load(task.result)
                    .into(imageViewFromFB)
            } else {

            }
        })
    }

    private fun getBoardData(key: String) {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // 데이터를 삭제한 경우는 getValue() 결과가 null 이다.
                if (dataSnapshot.getValue() != null) {
                    Log.d(TAG, dataSnapshot.toString())
                    val dataModel = dataSnapshot.getValue(BoardModel::class.java)
                    Log.d(TAG, dataModel!!.title)

                    binding.textTitle.setText(dataModel!!.title)
                    binding.textContent.setText(dataModel!!.content)
                    writerUid = dataModel!!.uid
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }

        FBRef.boardRef.child(key).addValueEventListener(postListener)
    }
}