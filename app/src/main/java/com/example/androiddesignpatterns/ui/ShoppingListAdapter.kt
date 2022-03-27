package com.example.androiddesignpatterns.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddesignpatterns.R
import com.example.androiddesignpatterns.data.db.entities.ShoppingItem

class ShoppingListAdapter(
    private val items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingViewHolder>() {


    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {

        val curShoppingItem = items[position]

        holder.itemView.findViewById<TextView>(R.id.tvName).text = curShoppingItem.name
        holder.itemView.findViewById<TextView>(R.id.tvAmount).text = "${curShoppingItem.amount}"

        holder.itemView.findViewById<ImageView>(R.id.ivDelete).setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

        holder.itemView.findViewById<ImageView>(R.id.ivPlus).setOnClickListener {
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }

        holder.itemView.findViewById<ImageView>(R.id.ivMinus).setOnClickListener {
            if (curShoppingItem.amount > 0) {
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}