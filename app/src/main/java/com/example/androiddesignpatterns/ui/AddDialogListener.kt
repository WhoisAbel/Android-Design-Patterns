package com.example.androiddesignpatterns.ui

import com.example.androiddesignpatterns.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}