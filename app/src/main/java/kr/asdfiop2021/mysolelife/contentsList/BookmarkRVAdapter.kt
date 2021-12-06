package kr.asdfiop2021.mysolelife.contentsList

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.asdfiop2021.mysolelife.R
import kr.asdfiop2021.mysolelife.utils.FBAuth
import kr.asdfiop2021.mysolelife.utils.FBRef

class BookmarkRVAdapter(val context : Context,
                       val items:ArrayList<ContentModel>,
                       val keyList:ArrayList<String>,
                       val bookmarkIdList : MutableList<String>) : RecyclerView.Adapter<BookmarkRVAdapter.ViewHolder>(){

    /*  // 동작이 안되게 수정
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null
    */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)
        Log.d("BookmarkRVAdapter", keyList.toString())
        Log.d("BookmarkRVAdapter", bookmarkIdList.toString())
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: BookmarkRVAdapter.ViewHolder, position: Int) {

        /* // 동작이 안되게 수정
        if (itemClick != null) {
            holder.itemView.setOnClickListener { v->
                itemClick?.onClick(v, position)
            }
        }
         */
        holder.bindItems(items[position], keyList[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: ContentModel, key : String) {

            itemView.setOnClickListener {
                Toast.makeText(context, item.title, Toast.LENGTH_LONG).show()
                val intent = Intent(context, ContentShowActivity::class.java)
                intent.putExtra("url", item.webUrl)
                itemView.context.startActivity(intent)
            }

            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            contentTitle.text = item.title

            var imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)

            Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)

            val bookmarkArea = itemView.findViewById<ImageView>(R.id.bookmarkArea)

            if (bookmarkIdList.contains(key)) {
                bookmarkArea.setImageResource(R.drawable.bookmark_color)
            } else {
                bookmarkArea.setImageResource(R.drawable.bookmark_white)
            }

        }
    }


}