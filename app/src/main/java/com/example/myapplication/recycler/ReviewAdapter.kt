package com.example.myapplication.recycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity2
import com.example.myapplication.R
import com.example.myapplication.data.database.Reviews.Review
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.review_item.view.*

class ReviewAdapter(private val postList :List<Review>): RecyclerView.Adapter<ReviewAdapter.ReviewItemViewHolder>() {

    class ReviewItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView =itemView.ReviewTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewItemViewHolder {
        return ReviewAdapter.ReviewItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewItemViewHolder, position: Int) {
        var reviewModel =  postList[position]
        holder.textViewTitle.text = reviewModel.movieReview

    }

    override fun getItemCount(): Int {
        return postList.size
    }
}