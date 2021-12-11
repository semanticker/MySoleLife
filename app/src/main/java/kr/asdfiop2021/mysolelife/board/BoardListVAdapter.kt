package kr.asdfiop2021.mysolelife.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kr.asdfiop2021.mysolelife.R

class BoardListVAdapter (val boardList : MutableList<BoardModel>) : BaseAdapter() {
    override fun getCount(): Int {
        TODO("Not yet implemented")
        return 10
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
        return boardList[position]
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("Not yet implemented")
        var converView = convertView
        if (converView == null) {
            converView = LayoutInflater.from(parent?.context).inflate(R.layout.board_list_item, parent,false)
        }

        return converView!!
    }

}