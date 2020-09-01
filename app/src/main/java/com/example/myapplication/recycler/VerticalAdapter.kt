package com.example.myapplication.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.vertical_item.view.*

class VerticalAdapter (private val context: Context, private val postList :List<VerticalModel>): RecyclerView.Adapter<VerticalAdapter.ItemViewHolder>() {


        class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
              val myRecycler:RecyclerView = itemView.recycler_view_1
            val text : TextView = itemView.text1
            val button: Button = itemView.btn
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.vertical_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
       val verticalModel: VerticalModel = arrayList.get(position)
        val title: String = vertivalModel.getTitle()
        val singleItem : ArrayList<HorizntalModel> = verticalModel.getArrayList()
        holder.text.setText(title)
        holder.myRecycler.setHasFixedSize(true)
        holder.myRecycler.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false))
        holder.myRecycler.setAdapter(HorizentalAdapter)
        holder.button.setOnClickListener(
        {
            @Override
            fun onClick(v: View) {
                Toast.makeText(context,VerticalModel.getName(), Toast.LENGTH_SHORT).show()
            }


        )
    }

    override fun getItemCount(): Int {
       VetricalModel.size
    }

}