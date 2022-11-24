package com.example.composedemo.ui.activity.pagination_demo

import kotlinx.coroutines.delay


class PagingRepository {
    private val remoteDataSource = (1..100).map {
        ListItem(
            title = "Item $it",
            description = "Description $it"
        )
    }

    suspend fun getItem(page: Int, pageSize: Int): Result<List<ListItem>> {
        delay(1000)
        val startingIndex=page*pageSize
        return if (startingIndex + pageSize<=remoteDataSource.size){
            Result.success(remoteDataSource.slice(startingIndex until startingIndex+pageSize))
        }else{
            Result.success(emptyList())
        }
    }
}