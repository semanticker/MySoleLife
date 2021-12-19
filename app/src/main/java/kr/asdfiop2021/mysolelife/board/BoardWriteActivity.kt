package kr.asdfiop2021.mysolelife.board

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kr.asdfiop2021.mysolelife.R
import kr.asdfiop2021.mysolelife.contentsList.BookmarkModel
import kr.asdfiop2021.mysolelife.databinding.ActivityBoardWriteBinding
import kr.asdfiop2021.mysolelife.utils.FBAuth
import kr.asdfiop2021.mysolelife.utils.FBRef
import java.io.ByteArrayOutputStream

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBoardWriteBinding

    private var TAG = BoardWriteActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_board_write)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_write)

        binding.btnWrite.setOnClickListener {
            var title = binding.textTitle.text.toString()
            var content = binding.textContent.text.toString()
            var uid = FBAuth.getUid()
            var time = FBAuth.getTime()

            Log.d(TAG, title);
            Log.d(TAG, content);
            Log.d(TAG, uid);
            Log.d(TAG, time);

            val key = FBRef.boardRef.push().key.toString()

            // 북마크에 등록이 되어 있지 않은 경우
            /*
                board
                    key
                        boardModel(title, content, uid, time)
             */
            FBRef.boardRef
                //.push()
                .child(key)
                .setValue(BoardModel(title, content, uid, time))

            Toast.makeText(this, "게시글 입력 완료", Toast.LENGTH_LONG).show()

            imageUpload(key)

            finish()
        }

        binding.imgPlus.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }
    }

    private fun imageUpload(key : String){

        val storage = Firebase.storage

        val storageRef = storage.reference
        //val mountainsRef = storageRef.child("mountains.jpg")
        val mountainsRef = storageRef.child(key + ".png")

        val imageView = binding.imgPlus

        // Get the data from an ImageView as bytes
        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == 100) {
            binding.imgPlus.setImageURI(data?.data)
        }
    }
}