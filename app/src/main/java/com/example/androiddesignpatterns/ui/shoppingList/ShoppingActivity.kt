package com.example.androiddesignpatterns.ui.shoppingList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.androiddesignpatterns.R
import com.example.androiddesignpatterns.data.db.ShoppingDatabase
import com.example.androiddesignpatterns.data.repositories.ShoppingRepository

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory)[ShoppingViewModel::class.java]

    }
}