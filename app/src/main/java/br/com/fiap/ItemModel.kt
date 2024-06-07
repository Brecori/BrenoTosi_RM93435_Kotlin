package com.example.gskotlin

data class ItemModel (
    val praia: String,
    val cidade: String,
    val estado: String,
    val onRemove: (ItemModel) -> Unit
)