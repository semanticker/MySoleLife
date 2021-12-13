package kr.asdfiop2021.mysolelife.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kr.asdfiop2021.mysolelife.R
import kr.asdfiop2021.mysolelife.board.BoardListVAdapter
import kr.asdfiop2021.mysolelife.board.BoardModel
import kr.asdfiop2021.mysolelife.board.BoardWriteActivity
import kr.asdfiop2021.mysolelife.contentsList.ContentModel
import kr.asdfiop2021.mysolelife.databinding.FragmentHomeBinding
import kr.asdfiop2021.mysolelife.databinding.FragmentTalkBinding
import kr.asdfiop2021.mysolelife.utils.FBRef

/**
 * A simple [Fragment] subclass.
 * Use the [TalkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TalkFragment : Fragment() {

    private lateinit var binding : FragmentTalkBinding

    private val boardDataList = mutableListOf<BoardModel>()

    private lateinit var boardRVAdater : BoardListVAdapter

    private var TAG = TalkFragment::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_talk, container, false)

        //val boardList = mutableListOf<BoardModel>()
        //boardList.add(BoardModel("a", "B", "c", "d"))

        boardRVAdater = BoardListVAdapter(boardDataList)
        binding.listViewBoard.adapter = boardRVAdater

        binding.btnWrite.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        binding.tabHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_homeFragment)
        }
        binding.tabTip.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_tipFragment)
        }
        binding.tabBookmark.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_bookmarkFragment)
        }
        binding.tabStore.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_storeFragment)
        }

        getFBBoardData()

        return binding.root
    }

    private fun getFBBoardData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                boardDataList.clear()

                Log.d("ContentListActivity", dataSnapshot.toString())

                for (dataModel in dataSnapshot.children) {
                    Log.d(TAG, dataModel.toString())

                    Log.d("ContentListActivity", dataModel.toString())
                    Log.d("ContentListActivity", dataModel.key.toString())
                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)
                }

                boardRVAdater.notifyDataSetChanged()

                Log.d(TAG, boardDataList.toString())

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }

        FBRef.boardRef.addValueEventListener(postListener)
    }
}