package com.example.androiddesignpatterns.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddesignpatterns.R
import com.example.androiddesignpatterns.data.db.ShoppingDatabase
import com.example.androiddesignpatterns.data.db.entities.ShoppingItem
import com.example.androiddesignpatterns.data.repositories.ShoppingRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShoppingActivity : AppCompatActivity() {
    @SuppressLint("CutPasteId", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory)[ShoppingViewModel::class.java]


        val adapter = ShoppingListAdapter(listOf(), viewModel)
        findViewById<RecyclerView>(R.id.rvShoppingItems).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.rvShoppingItems).adapter = adapter

        viewModel.getAllShoppingItems().observe(this) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }

    }
}