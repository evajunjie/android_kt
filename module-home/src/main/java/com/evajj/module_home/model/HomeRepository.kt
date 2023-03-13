package com.evajj.module_home.model

import com.evajj.ktbase.base.BaseModel
import com.evajj.module_home.data.entity.WanItem
import com.evajj.module_home.data.source.HttpDataSource
import com.evajj.module_home.data.source.LocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Author:wenjunjie
 * Date:2023/2/21
 * Time:下午6:09
 * Description:
 **/
@Singleton
class HomeRepository @Inject constructor(
    private val localDataSource: LocalDataSource, private val httpDataSource: HttpDataSource
) : BaseModel(), LocalDataSource, HttpDataSource {
    override suspend fun loadNetWorkData() =
        httpDataSource.loadNetWorkData()

    override suspend fun loadLocalData() = localDataSource.loadLocalData()

    override suspend fun saveLocalData(data: WanItem) {
        localDataSource.saveLocalData(data)
    }


}