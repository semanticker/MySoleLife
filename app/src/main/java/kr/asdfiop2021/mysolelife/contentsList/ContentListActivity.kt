package kr.asdfiop2021.mysolelife.contentsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.asdfiop2021.mysolelife.R

class ContentListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents_list)

        val rv : RecyclerView = findViewById(R.id.recyclerView)

        val items = ArrayList<ContentModel>()
        items.add(ContentModel("imageUrl1", "title1"))
        items.add(ContentModel("imageUrl2", "title2"))
        items.add(ContentModel("imageUrl3", "title3"))


        val rvAdapter = ContentRVAdapter(items);
        rv.adapter = rvAdapter

        rv.layoutManager = GridLayoutManager(this, 2)

    }
}