package com.example.composedemo.ui.activity.pagination_demo

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}