package com.evajj.module_home.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.evajj.ktbase.database.BaseDao
import com.evajj.module_home.data.entity.WanItem
import kotlinx.coroutines.flow.Flow

/**
 * Author:wenjunjie
 * Date:2023/3/13
 * Time:上午11:39
 * Description:
 **/
@Dao
interface HomeDao :BaseDao<WanItem>{
    @Query("Select * from Wan")
    suspend fun getAllData() : List<WanItem>

    @Insert
    suspend fun saveData(item: WanItem?) //插入单条数据

}