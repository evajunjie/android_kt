package com.evajj.ktbase.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Author:wenjunjie
 * Date:2023/2/13
 * Time:下午3:50
 * Description:
 **/
interface BaseDao<T> {
    @Insert
    fun insertItem(item: T?): Flow<T?> //插入单条数据


    @Insert
    fun insertItems(items: List<T?>?): Flow<List<T?>> //插入list数据


    @Delete
    fun deleteItem(item: T?): Flow<T?> //删除item


    @Update
    fun updateItem(item: T?): Flow<T?> //更新item

}