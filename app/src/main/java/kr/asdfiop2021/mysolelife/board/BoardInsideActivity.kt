package kr.asdfiop2021.mysolelife.board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kr.asdfiop2021.mysolelife.R
import kr.asdfiop2021.mysolelife.databinding.ActivityBoardInsideBinding
import kr.asdfiop2021.mysolelife.utils.FBAuth
import kr.asdfiop2021.mysolelife.utils.FBRef

class BoardInsideActivity : AppCompatActivity() {

    private val TAG = BoardInsideActivity::class.java.simpleName

    private lateinit var binding : ActivityBoardInsideBinding

    private lateinit var key:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_inside)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_inside)

        /*
        첫번째 방법
        val title = intent.getStringExtra("title").toString()
        val content = intent.getStringExtra("content").toString()
        val time = intent.getStringExtra("time").toString()

        binding.textTitle.text = title
        binding.textTime.text = time
        binding.textContent.text = content
        */
        binding.imageBoardMenu.setOnClickListener {
            showDialog()
        }

        // 두번째 방법
        key = intent.getStringExtra("key").toString()
        getBoardData(key)

        getImageData(key)


    }

    private fun showDialog(){

        val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("게시글 수정/삭제")

        val alertDialog = mBuilder.show()
        alertDialog.findViewById<Button>(R.id.btnEdit)?.setOnClickListener {

            Toast.makeText(this, "수정", Toast.LENGTH_LONG).show()

            val intent = Intent(this, BoardEditActivity::class.java)
            intent.putExtra("key", key)
            startActivity(intent)
        }

        alertDialog.findViewById<Button>(R.id.btnDelete)?.setOnClickListener {
            FBRef.boardRef.child(key).removeValue()
            Toast.makeText(this, "삭제", Toast.LENGTH_LONG).show()
            alertDialog.dismiss()
        }

    }

    private fun getImageData(key: String) {

        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child(key + ".png")

        // ImageView in your Activity
        val imageView = findViewById<ImageView>(R.id.imageArea)

        val imageViewFromFB = binding.imageDownload


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
                binding.imageDownload.isVisible = false
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

                    binding.textTitle.text = dataModel!!.title
                    binding.textContent.text = dataModel!!.content
                    binding.textTime.text = dataModel!!.time

                    val myUid = FBAuth.getUid()
                    val writerUid = dataModel.uid

                    if (myUid.equals(writerUid)) {
                        Toast.makeText(baseContext, "내글", Toast.LENGTH_LONG)
                        binding.imageBoardMenu.isVisible = true
                    } else {
                        Toast.makeText(baseContext, "내글 아님", Toast.LENGTH_LONG)
                        binding.imageBoardMenu.isVisible = false
                    }
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