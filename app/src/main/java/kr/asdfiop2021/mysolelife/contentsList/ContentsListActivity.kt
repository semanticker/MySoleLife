package kr.asdfiop2021.mysolelife.contentsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.asdfiop2021.mysolelife.R

class ContentsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents_list)

        val rv : RecyclerView = findViewById(R.id.recyclerView)

        val items = ArrayList<String>()
        items.add("a")
        items.add("b")
        items.add("c")


        val rvAdapter = ContentRVAdapter(items);
        rv.adapter = rvAdapter

        rv.layoutManager = GridLayoutManager(this, 2)

    }
}