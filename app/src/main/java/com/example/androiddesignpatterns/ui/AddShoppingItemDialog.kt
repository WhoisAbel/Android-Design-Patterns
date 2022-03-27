package com.example.androiddesignpatterns.ui

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.androiddesignpatterns.R
import com.example.androiddesignpatterns.data.db.entities.ShoppingItem

class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        findViewById<TextView>(R.id.tvAdd)?.setOnClickListener {
            val name = findViewById<EditText>(R.id.etName)?.text.toString()
            val amount = findViewById<EditText>(R.id.etAmount)?.text.toString()

            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Please fill all the information", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name = name, amount = amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        findViewById<TextView>(R.id.tvCancel)?.setOnClickListener {
            cancel()
        }
    }
}