package com.evajj.module_home.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evajj.module_home.data.dao.HomeDao
import com.evajj.module_home.data.entity.WanItem

/**
 * Author:wenjunjie
 * Date:2023/3/13
 * Time:下午2:35
 * Description:
 **/
@Database(entities = [WanItem::class], version = 1, exportSchema = false)
abstract class HomeDataBase :RoomDatabase(){
    abstract fun getHomeDao() :HomeDao
}