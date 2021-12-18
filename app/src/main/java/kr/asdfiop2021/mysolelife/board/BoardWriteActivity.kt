package kr.asdfiop2021.mysolelife.board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import kr.asdfiop2021.mysolelife.R
import kr.asdfiop2021.mysolelife.contentsList.BookmarkModel
import kr.asdfiop2021.mysolelife.databinding.ActivityBoardWriteBinding
import kr.asdfiop2021.mysolelife.utils.FBAuth
import kr.asdfiop2021.mysolelife.utils.FBRef

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

            // 북마크에 등록이 되어 있지 않은 경우
            /*
                board
                    key
                        boardModel(title, content, uid, time)
             */
            FBRef.boardRef
                .push()
                .setValue(BoardModel(title, content, uid, time))

            Toast.makeText(this, "게시글 입력 완료", Toast.LENGTH_LONG).show()

            finish()
        }

        binding.imgPlus.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == 100) {
            binding.imgPlus.setImageURI(data?.data)
        }
    }
}