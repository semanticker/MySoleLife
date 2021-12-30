package kr.asdfiop2021.mysolelife.board

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import kr.asdfiop2021.mysolelife.R
import kr.asdfiop2021.mysolelife.utils.FBAuth

class BoardListVAdapter (val boardList : MutableList<BoardModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return boardList.size
    }

    override fun getItem(position: Int): Any {
        return boardList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.board_list_item, parent,false)
        }

        val itemLinearLayoutView = view?.findViewById<LinearLayout>(R.id.itemView)

        val title = view?.findViewById<TextView>(R.id.textTitle)
        title!!.text = boardList[position].title

        val content = view?.findViewById<TextView>(R.id.textContent)
        content!!.text = boardList[position].content

        val time = view?.findViewById<TextView>(R.id.textContent)
        time!!.text = boardList[position].time

        if (boardList[position].uid.equals(FBAuth.getUid())) {
            itemLinearLayoutView?.setBackgroundColor(Color.parseColor("#ffa500"))
        }

        return view!!
    }

}