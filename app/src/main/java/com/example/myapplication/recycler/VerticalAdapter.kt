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

class VerticalAdapter(private val context: Context, private val postList: ArrayList<MoviesDetails>): RecyclerView.Adapter<VerticalAdapter.ItemViewHolder>() {


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

        val listOfLists = divideList(postList)

        val lang =ArrayList<String>()
        lang.add("en")
        lang.add("es")
        lang.add("ko")

        listOfLists.forEachIndexed { index, arrayList ->
            holder.text.setText(lang[index])
            holder.myRecycler.setHasFixedSize(true)
            holder.myRecycler.setLayoutManager(
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            )
            holder.myRecycler.setAdapter(HorizentalAdapter(context,arrayList))
        }

    }
    override fun getItemCount(): Int {
        return postList.size
    }
    private fun divideList(postList: List<MoviesDetails>): ArrayList<ArrayList<MoviesDetails>>{

        val en =ArrayList<MoviesDetails>()
        val es =ArrayList<MoviesDetails>()
        val ko =ArrayList<MoviesDetails>()

        for (movie in postList){
            when (movie.originalLanguage) {
                "en" -> en.add(movie)
                "es" -> es.add(movie)
                "ko" -> ko.add(movie)
            }
        }
        val listOfLists= ArrayList<ArrayList<MoviesDetails>>()
        listOfLists.add(en)
        listOfLists.add(es)
        listOfLists.add(ko)

        return listOfLists
    }

  }

