package kr.asdfiop2021.mysolelife.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kr.asdfiop2021.mysolelife.R
import kr.asdfiop2021.mysolelife.databinding.ActivityBoardInsideBinding
import kr.asdfiop2021.mysolelife.utils.FBRef

class BoardInsideActivity : AppCompatActivity() {

    private val TAG = BoardInsideActivity::class.java.simpleName

    private lateinit var binding : ActivityBoardInsideBinding

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

        // 두번째 방법
        val key = intent.getStringExtra("key")
        getBoardData(key.toString())
    }

    private fun getBoardData(key: String) {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                Log.d(TAG, dataSnapshot.toString())
                val dataModel = dataSnapshot.getValue(BoardModel::class.java)
                Log.d(TAG, dataModel!!.title)

                binding.textTitle.text = dataModel!!.title
                binding.textContent.text = dataModel!!.content
                binding.textTime.text = dataModel!!.time

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }

        FBRef.boardRef.child(key).addValueEventListener(postListener)
    }
}