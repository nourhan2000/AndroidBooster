package com.example.myapplication.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.network.modules.MoviesDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.vertical_item.view.*

class VerticalAdapter(private val context: Context, private val postList: List<MoviesDetails>): RecyclerView.Adapter<VerticalAdapter.ItemViewHolder>() {


        class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
              val myRecycler:RecyclerView = itemView.recycler_view_1
            val text : TextView = itemView.text1
              val button = itemView.btn
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.vertical_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val lang =ArrayList<String>()
        lang.add("en")
        lang.add("es")
        lang.add("ko")
        val en =ArrayList<MoviesDetails>()
        val es =ArrayList<MoviesDetails>()
        val ko =ArrayList<MoviesDetails>()
        val listOfLists= ArrayList<ArrayList<MoviesDetails>>()

        for(list in listOfLists){

            holder.text.setText(lang[position])
            holder.myRecycler.setHasFixedSize(true)
            holder.myRecycler.setLayoutManager(
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            )
            holder.myRecycler.setAdapter(HorizentalAdapter(context,list))
        }

       //val verticalModel: MoviesDetails = postList[position]
        //val title: String = MoviesDetails.getTitle()
        //val singleItem : ArrayList<HorizntalModel> = verticalModel.getArrayList()
        //holder.text.setText(title)

    }
    override fun getItemCount(): Int {
        return postList.size
    }

  }

