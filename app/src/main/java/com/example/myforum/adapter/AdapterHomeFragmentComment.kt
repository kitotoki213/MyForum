package com.example.myforum.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myforum.R
import com.example.myforum.data.Comment

// RecyclerView adapter
class AdapterHomeFragmentComment(commentList: List<Comment>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var currentComments = commentList

    class CommentViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvCommentTitle = view.findViewById<TextView>(R.id.tvCommentTitle)
        val tvCommentAuthor = view.findViewById<TextView>(R.id.tvCommentAuthor)
        val tvCommunity = view.findViewById<TextView>(R.id.tvCommunity)
        val tvThumcount = view.findViewById<TextView>(R.id.tvThumcount)
        val tvContent = view.findViewById<TextView>(R.id.tvContent)

        fun bindData(comment: Comment) {
            tvCommentTitle.text = comment.title
            tvCommentAuthor.text = comment.author
            tvCommunity.text = comment.community
            tvThumcount.text = comment.thumbCount.toString()
            tvContent.text = comment.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_home_fragment_comment, parent, false).run {
            CommentViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CommentViewHolder).bindData(currentComments[position])
    }

    override fun getItemCount(): Int = currentComments.size
}