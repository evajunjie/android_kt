package com.evajj.module_home.data.source

import com.evajj.module_home.data.entity.WanItem
import kotlinx.coroutines.flow.Flow

/**
 * Author:wenjunjie
 * Date:2023/2/21
 * Time:下午5:47
 * Description:
 **/
interface LocalDataSource {
    suspend fun loadLocalData() : Flow<List<WanItem>>

    suspend fun saveLocalData(data : WanItem)
}