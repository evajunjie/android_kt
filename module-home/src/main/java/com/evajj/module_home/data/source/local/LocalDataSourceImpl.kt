package com.evajj.module_home.data.source.local

import com.evajj.module_home.data.dao.HomeDao
import com.evajj.module_home.data.entity.WanItem
import com.evajj.module_home.data.source.HttpDataSource
import com.evajj.module_home.data.source.LocalDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Author:wenjunjie
 * Date:2023/2/21
 * Time:下午5:55
 * Description:
 **/
class LocalDataSourceImpl @Inject constructor(private val homeDao: HomeDao) : LocalDataSource {
    override suspend fun loadLocalData() = flow {
        emit(homeDao.getAllData())
    }
    override suspend fun saveLocalData(data: WanItem) {
        homeDao.saveData(data)
    }
}