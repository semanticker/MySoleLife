package kr.asdfiop2021.mysolelife.contentsList

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.asdfiop2021.mysolelife.R

class ContentRVAdapter(val context : Context, val items:ArrayList<ContentModel>, val keyList:ArrayList<String>) : RecyclerView.Adapter<ContentRVAdapter.ViewHolder>(){

    /*  // 동작이 안되게 수정
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null
    */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContentRVAdapter.ViewHolder, position: Int) {

        /* // 동작이 안되게 수정
        if (itemClick != null) {
            holder.itemView.setOnClickListener { v->
                itemClick?.onClick(v, position)
            }
        }
         */
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: ContentModel) {

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
            bookmarkArea.setOnClickListener {
                Toast.makeText(context, "북마크가 클릭되었습니다.", Toast.LENGTH_LONG).show()
            }


        }

    }


}